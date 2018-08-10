package com.example.demo.dubbo.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.demo.disconf.provider.DemoProvider;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiangzhurui
 * @version 2018/8/10 14:49
 */
@RestController
@RequestMapping("/test")
public class ConsumerController {

    @Reference(
            version = "1.0.0"
    )
    private DemoProvider demoService;

    @RequestMapping("hello/{input}")
    public Object test(@PathVariable String input) {
        String s = demoService.sayHello(input);
        System.out.println(s);
        return s;
    }

}
