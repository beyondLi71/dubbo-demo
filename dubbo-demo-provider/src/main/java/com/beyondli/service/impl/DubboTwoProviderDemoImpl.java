package com.beyondli.service.impl;/**
 * Created by beyondLi on 2021/7/13 11:28
 */

import com.beyondli.dubbo.DubboDemo;
import com.beyondli.dubbo.DubboTwoDemo;
import org.apache.dubbo.config.annotation.Service;

/**
 * @Description: Dubbo生产者demo
 * @ClassName: DubboProviderDemoImpl
 * @Author: beyondLi
 * @Date: 2021/7/13 11:28   
 */
@Service
public class DubboTwoProviderDemoImpl implements DubboTwoDemo {

    @Override
    public String sayHello(String name) {
        return name;
    }

    @Override
    public String sayBey(String name) {
        return name;
    }
}
