package com.refactoring.rxo.esi.demo;


import com.refactoring.rxo.feign.FeignConfig;
import com.refactoring.rxo.vo.demo.Demo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: fei.yao
 * @date: 2018/6/6
 * @modified by:
 */
@FeignClient(name = "${feign.gateway.server-id}", configuration = FeignConfig.class)
public interface FeignDemoClient {
    @GetMapping("/${feign.client.server-id}/demo/test/{param}")
    ResponseEntity<Demo> test(@PathVariable("param") String param);

}
