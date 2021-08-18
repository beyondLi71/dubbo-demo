package com.beyondli.service.impl;/**
 * Created by beyondLi on 2021/7/13 11:28
 */

import com.beyondli.common.exception.ExceptionManager;
import com.beyondli.common.tools.apiresult.AbstractApiResult;
import com.beyondli.dubbo.DubboDemo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: Dubbo生产者demo
 * @ClassName: DubboProviderDemoImpl
 * @Author: beyondLi
 * @Date: 2021/7/13 11:28   
 */
@Service
public class DubboProviderDemoImpl implements DubboDemo {

    @Resource
    ExceptionManager exceptionManager;

    @Override
    public AbstractApiResult<String> sayHello(String name) {
        //int i = 1 / 0;
        if (1==1) {
            throw exceptionManager.createByCode("DEMO_00006");
        }
        return AbstractApiResult.success(name);
    }

    @Override
    public String sayBey(String name) {
        return name;
    }
}
