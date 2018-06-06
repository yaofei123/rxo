package com.refactoring.rxo.business;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.refactoring.rxo.esi.demo.FeignDemoClient;
import com.refactoring.rxo.vo.demo.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: fei.yao
 * @date: 2018/6/6
 * @modified by:
 */
@RestController
@RequestMapping("/api")
public class FeignDemoController {

    @Autowired
    private FeignDemoClient feignDemoService;

    @RequestMapping("/test/{param}")
    @HystrixCommand(fallbackMethod="fallback2()")
    public ResponseEntity<Demo> test(@PathVariable("param") String param){
        return feignDemoService.test(param);
    }

    public String fallback2(String name,String pwd){

        return "===== fallback2 ======= "+name+" "+pwd+" login success ============";

    }

}
