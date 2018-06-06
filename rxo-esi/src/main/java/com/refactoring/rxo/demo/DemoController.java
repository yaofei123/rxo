package com.refactoring.rxo.demo;


import com.refactoring.rxo.vo.demo.Demo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @author: fei.yao
 * @date: 2018/6/5
 * @modified by:
 */
@RestController
@RequestMapping(value = "/demo")
public class DemoController {

    @GetMapping("/test/{param}")
    @ResponseBody
    public ResponseEntity<Demo> test(@PathVariable("param") String parma){
        Demo demo = new Demo();
        demo.setName(parma);
        demo.setValue(parma+"1");
        return new ResponseEntity<>(demo,HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Demo> test2(){
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Demo demo = new Demo();
        return new ResponseEntity<>(demo,HttpStatus.OK);
    }

}
