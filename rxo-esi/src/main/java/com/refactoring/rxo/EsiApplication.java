package com.refactoring.rxo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: fei.yao
 * @date: 2018/6/5
 * @modified by:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class EsiApplication {


    public static void main(String[] args) {
        SpringApplication.run(EsiApplication.class, args);
    }
}
