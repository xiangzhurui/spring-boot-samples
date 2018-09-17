package com.xiangzhurui.example.demo.restful.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class RestFulExampleController {

    @RequestMapping(value = "/populateDataFromServer/{name}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public List<Integer> populateActivePSwapBasketGET(@PathVariable String name) {

        return getIntegers(name);

    }

    @RequestMapping(value = "/populateDataFromServer", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public List<Integer> populateActivePSwapBasketPOST(@RequestParam String name) {

        return getIntegers(name);
    }

    @RequestMapping(value = "/populateDataFromServer/{name}", method = RequestMethod.PUT, produces = {"application/json"})
    @ResponseBody

    public List<Integer> populateActivePSwapBasketPUT(@PathVariable String name) {

        return getIntegers(name);
    }

    @RequestMapping(value = "/populateDataFromServer/{name}", method = RequestMethod.DELETE, produces = {"application/json"})
    @ResponseBody
    public List<Integer> populateActivePSwapBasketDelete(@PathVariable String name) {

        return getIntegers(name);
    }

    private List<Integer> getIntegers(@PathVariable String name) {
        if (name.equalsIgnoreCase("JavaHonk")) {
            return returnDataList();
        } else {
            List<Integer> list = new ArrayList<Integer>();
            list.add(12345);
            return list;
        }
    }

    private List<Integer> returnDataList() {
        Random rand = new Random();
        Integer randomNum = rand.nextInt();
        List<Integer> list = new ArrayList<Integer>();
        list.add(randomNum);
        return list;
    }

}
