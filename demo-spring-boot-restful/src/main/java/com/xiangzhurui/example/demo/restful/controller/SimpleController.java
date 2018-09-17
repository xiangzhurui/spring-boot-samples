package com.xiangzhurui.example.demo.restful.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiangzhurui
 * @version 2018/9/6 21:23
 */
@Slf4j
@RestController
@RequestMapping("/simple")
public class SimpleController {


    @RequestMapping(
            path = "get",
            method = RequestMethod.GET

    )
    public List<String> getAll() {
        return new ArrayList<String>() {{
            add("aba");
            add("1232");
        }};
    }


}
