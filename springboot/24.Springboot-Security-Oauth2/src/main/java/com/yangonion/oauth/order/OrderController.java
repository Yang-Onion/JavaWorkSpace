package com.yangonion.oauth.order;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @RequestMapping("/product/{id}")
    public  String getProduct(@PathVariable String id){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        return  "product id:"+id;
    }

    @RequestMapping("/order/{id}")
    public String getOrder(@PathVariable String id){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        return  "order id:"+id;
    }
}
