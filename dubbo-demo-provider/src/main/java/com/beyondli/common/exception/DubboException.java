package com.beyondli.common.exception;/**
 * Created by beyondLi on 2021/7/26 18:34
 */

import com.alibaba.fastjson.JSON;
import com.beyondli.common.utils.uuid.UUIDUtils;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @ClassName: DubboException
 * @Author: beyondLi
 * @Date: 2021/7/26 18:34   
 */
@Aspect
@Component
public class DubboException {

    @Value("${spring.application.name}")
    private String appName;


    @AfterThrowing(throwing = "ex", pointcut = "execution(* com.beyondli.service..*(..))")
    public void afterThrow(Throwable ex) {
        if (!(ex instanceof PhantomException)) {
            System.out.println(ex);
            PhantomException exception = new PhantomException(UUIDUtils.getUUID(), appName, "SYSTEM_ERR", "STSTEM_ERROR");
            throw exception;
        }


        //exception.setStackTrace(exc.getStackTrace());
        //PhantomException phantomException = exceptionManager.create(exception);
        //log.error(logTraceInfo(phantomException));
        //log.error(logTraceInfo(e));
        //return JSON.parseObject(phantomException.toString());
    }

}
