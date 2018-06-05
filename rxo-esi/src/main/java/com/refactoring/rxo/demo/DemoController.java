package com.refactoring.rxo.demo;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: fei.yao
 * @date: 2018/6/5
 * @modified by:
 */
@RestController
@RequestMapping(value = "/demo")
public class DemoController {

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping()
    public String test2(){
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "test2";
    }

}
