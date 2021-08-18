package com.beyondli.common.exception;


import com.beyondli.common.utils.uuid.UUIDUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author beyondLi
 * @desc 异常工厂.
 */
@Component
@PropertySource(value = "classpath:exception.properties", encoding = "UTF-8")
public class ExceptionManager {

    @Value("${spring.application.name}")
    private String appName;

    @Resource
    Environment environment;

    /**
     * 创建默认的异常
     * @param code
     * @return
     */
    public PhantomException createByCode(String code) {
        return new PhantomException(UUIDUtils.getUUID(), appName, code, environment.getProperty(code));
    }

    public PhantomException createByMessage(String message) {
        return new PhantomException(UUIDUtils.getUUID(), appName, "CUSTOM_ERR", message);
    }

    /**
     * 简化异常栈信息
     * @param pe
     * @return
     */
    protected PhantomException create(PhantomException pe) {
        List<StackTraceElement> traceList = Stream.of(pe.getStackTrace())
                .filter(p -> p.getClassName().contains("com.maxrocky"))
                .filter(p -> !p.getClassName().contains("$"))
                .filter(p -> !p.getClassName().contains(".exception."))
                .collect(Collectors.toList());
        pe.setStackTrace(traceList.toArray(new StackTraceElement[]{}));
        return pe;
    }

}
