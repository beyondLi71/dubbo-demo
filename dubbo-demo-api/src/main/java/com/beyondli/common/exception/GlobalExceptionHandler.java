package com.beyondli.common.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.beyondli.common.utils.uuid.UUIDUtils;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;

/**
 * @author beyondLi
 * @desc 全局异常捕捉并转换异常
 */
@Log4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Value("${spring.application.name}")
    private String appName;

    @Resource
    ExceptionManager exceptionManager;

    /**
     * 校验异常
     * @param e
     */
    @ExceptionHandler({ConstraintViolationException.class,
            MethodArgumentNotValidException.class,
            ServletRequestBindingException.class,
            BindException.class})
    @ResponseStatus(OK)
    public JSONObject handleValidationException(Exception e) {
        PhantomException exception = null;

        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException t = (MethodArgumentNotValidException) e;
            FieldError fieldError = t.getBindingResult().getFieldErrors().stream().findFirst().orElse(null);
            if (fieldError != null) {
                String code = fieldError.getDefaultMessage();
                exception = exceptionManager.createByCode(code);
            }
        } else if (e instanceof BindException) {
            BindException t = (BindException) e;
            FieldError fieldError = t.getBindingResult().getFieldErrors().stream().findFirst().orElse(null);
            if (fieldError != null) {
                String code = fieldError.getDefaultMessage();
                exception = exceptionManager.createByCode(code);
            }
        } else if (e instanceof ConstraintViolationException) {
            ConstraintViolationException t = (ConstraintViolationException) e;
            String code = t.getConstraintViolations().stream().limit(1).map(vio -> vio.getMessageTemplate())
                    .collect(Collectors.toList()).get(0);
            exception = exceptionManager.createByCode(code);
        }
        /*else if (e instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException t = (MissingServletRequestParameterException) e;
            msg = t.getParameterName() + " 不能为空";
        } else if (e instanceof MissingPathVariableException) {
            MissingPathVariableException t = (MissingPathVariableException) e;
            msg = t.getVariableName() + " 不能为空";
        }*/
        else {
            exception = exceptionManager.createByMessage("参数校验异常");
        }

        if (exception == null) {
            exception = exceptionManager.createByMessage("参数校验异常");
        }

        exception.setStackTrace(e.getStackTrace());

        PhantomException phantomException = exceptionManager.create(exception);
        log.error(logTraceInfo(phantomException));
        return JSON.parseObject(phantomException.toString());
    }


    /**
     * 自定义异常
     * @param e
     */
    @ExceptionHandler(PhantomException.class)
    @ResponseStatus(OK)
    public JSONObject handlerException(PhantomException e) {
        PhantomException phantomException = exceptionManager.create(e);
        log.error(logTraceInfo(phantomException));
        log.error(phantomException.toString());
        return JSON.parseObject(phantomException.toString());
    }


    /**
     * 系统异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(OK)
    public JSONObject handleException(Exception e) {

        PhantomException exception = new PhantomException(UUIDUtils.getUUID(), appName, "SYSTEM_ERR", "STSTEM_ERROR");
        exception.setStackTrace(e.getStackTrace());
        PhantomException phantomException = exceptionManager.create(exception);
        log.error(logTraceInfo(phantomException));
        log.error(logTraceInfo(e));
        return JSON.parseObject(phantomException.toString());
    }


    /**
     * 实现异常栈信息打印，通过查看源码
     * @param e
     * @return
     */
    private String logTraceInfo(Exception e) {
        StackTraceElement[] trace = e.getStackTrace();
        StringBuilder sb = new StringBuilder();
        sb.append(e);
        for (StackTraceElement traceElement : trace) {
            sb.append("\n\t " + traceElement);
        }
        return sb.toString();
    }

}
