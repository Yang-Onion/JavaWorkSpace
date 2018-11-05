package com.yangonion.eurekaclient.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicReference;

@RestController
public class TestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DiscoveryClient client;

    @RequestMapping("/info")
    public String into(){

        AtomicReference<String> info = new AtomicReference<>("");
        client.getServices().forEach(id -> {
            client.getInstances(id).forEach(instance -> {
                info.set("/info, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
                logger.info("/info, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
            });
        });

        return info.toString();
    }

    @RequestMapping("/hello")
    public  String hello(){

        return "hello";
    }

}
