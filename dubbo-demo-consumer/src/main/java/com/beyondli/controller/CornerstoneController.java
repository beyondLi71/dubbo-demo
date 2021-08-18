package com.beyondli.controller;

import com.beyondli.common.utils.analysis.ThirdAnalysisUtils;
import com.beyondli.dubbo.DubboDemo;
import com.beyondli.dubbo.DubboTwoDemo;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

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

    @Reference
    DubboTwoDemo dubboTwoApi;

    @RequestMapping(value = "/get/info", method = RequestMethod.GET)
    public String get(@RequestParam("name")String name) {
        String info = ThirdAnalysisUtils.maxrockyAnalysis(dubboApi.sayHello(name));
        return info;
    }

    @RequestMapping(value = "/get/bey", method = RequestMethod.GET)
    public String bey(@RequestParam("name")String name) {
        String info = dubboApi.sayBey(name);
        return info;
    }

    @RequestMapping(value = "/get/two/info", method = RequestMethod.GET)
    public String getTwo(@RequestParam("name")String name) {
        String info = dubboTwoApi.sayHello(name);
        return info;
    }

    @RequestMapping(value = "/get/two/bey", method = RequestMethod.GET)
    public String beyTwo(@RequestParam("name")String name) {
        String info = dubboTwoApi.sayBey(name);
        return info;
    }
//
}
