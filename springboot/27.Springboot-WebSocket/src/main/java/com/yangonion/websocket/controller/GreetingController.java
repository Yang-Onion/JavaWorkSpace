package com.yangonion.websocket.controller;

import com.yangonion.websocket.model.RequestMessage;
import com.yangonion.websocket.model.ResponseMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

/**
 * @author Yang-Onion Github:https://www.github/yang-onion
 * @version 创建时间：2018/11/22 15:49
 */
@Controller
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greet")
    public ResponseMessage greet(RequestMessage request){
        System.out.println("收到"+request.getName()+"发送的消息！");
        ResponseMessage responseMessage=  new ResponseMessage();
        responseMessage.setContent("Hello,"+ HtmlUtils.htmlEscape(request.getName())+"!");
        return responseMessage;
    }
}
