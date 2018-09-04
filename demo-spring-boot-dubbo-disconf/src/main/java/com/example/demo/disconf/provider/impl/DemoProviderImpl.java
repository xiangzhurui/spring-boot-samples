package com.example.demo.disconf.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.demo.disconf.provider.DemoProvider;

/**
 * @author xiangzhurui
 * @version 2018/7/10 14:32
 */
@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class DemoProviderImpl implements DemoProvider {
    @Override
    public String sayHello(String input) {
        System.out.println(input);
        return "connect success!!" + input;
    }
}
