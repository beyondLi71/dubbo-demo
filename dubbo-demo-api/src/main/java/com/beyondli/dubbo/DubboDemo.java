package com.beyondli.dubbo;/**
 * Created by beyondLi on 2021/7/13 11:27
 */

import com.beyondli.common.tools.apiresult.AbstractApiResult;

/**
 * @Description: Dubbo生产者demo
 * @ClassName: DubboDemo
 * @Author: beyondLi
 * @Date: 2021/7/13 11:27   
 */
public interface DubboDemo {
    AbstractApiResult<String> sayHello(String name);

    String sayBey(String name);

}
