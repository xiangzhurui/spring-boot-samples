package com.xiangzhurui.example.web.service;

import org.springframework.scheduling.annotation.Async;

/**
 * @author xiangzhurui
 * @version 2017/12/16
 */
public interface SampleService {

    @Async
    void asyncMethod(Integer i);
}
