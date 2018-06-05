package com.refactoring.rxo.demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: fei.yao
 * @date: 2018/6/5
 * @modified by:
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
