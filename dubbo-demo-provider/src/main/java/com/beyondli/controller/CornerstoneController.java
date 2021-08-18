package com.beyondli.controller;

import com.beyondli.common.tools.apiresult.AbstractApiResult;
import com.beyondli.dubbo.DubboDemo;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 基础演示
 * @ClassName: CornerstoneController
 * @Author: beyondLi
 * @Date: 2021/1/14 14:03
 */
@RestController
@RequestMapping(value = "/dubbo")
public class CornerstoneController {

    @Reference
    DubboDemo dubboApi;

    @RequestMapping(value = "/get/info", method = RequestMethod.GET)
    public AbstractApiResult<String> get(@RequestParam("name")String name) {
        AbstractApiResult<String> info = dubboApi.sayHello(name);
        return info;
    }
//
}
