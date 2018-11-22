package com.yangonion.websocket.controller;

import com.yangonion.websocket.component.SocketSessionMap;
import com.yangonion.websocket.model.ChatMessage;
import com.yangonion.websocket.model.RequestMessage;
import com.yangonion.websocket.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

/**
 * @author Yang-Onion Github:https://www.github/yang-onion
 * @version 创建时间：2018/11/22 15:49
 */
@Controller
public class GreetingController {

    @Autowired
    private SocketSessionMap  socketSessionMap;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/hello")
    @SendTo("/topic/greet")
    public ResponseMessage greet(RequestMessage request){
        System.out.println("收到"+request.getName()+"发送的消息！");
        ResponseMessage responseMessage=  new ResponseMessage();
        responseMessage.setContent("Hello,"+ HtmlUtils.htmlEscape(request.getName())+"!");
        return responseMessage;
    }

    @MessageMapping("/chat")
    public  void chat(ChatMessage message){
        System.out.println(message.getFromId()+"-->"+message.getToId()+":"+message.getContent());
        String fromUserId = String.valueOf(message.getFromId());
        String toUserId = String.valueOf(message.getToId());
        String sendTo ="/topic/chat/"+toUserId;
        String content=fromUserId+":"+message.getContent();
        if (socketSessionMap.getUserSeesionId(toUserId) != null){
            messagingTemplate.convertAndSend(sendTo,HtmlUtils.htmlEscape(content));
        }
        else{

            sendTo ="/topic/chat/"+fromUserId;
            content="对方已下线";
            messagingTemplate.convertAndSend(sendTo,content);
        }

    }

    @MessageMapping("/offline")
    public  void offline(String userId){
        String sessionId =socketSessionMap.getUserSeesionId(userId);
        System.out.println("下线"+userId+"  "+ sessionId);
        socketSessionMap.removeSeesion(userId);
    }


    @RequestMapping("/chat/{id}")
    public String chatRoom(@PathVariable String id, ModelMap model){
        model.addAttribute("id",id);
        int onlineCount = socketSessionMap.onlineCount();
        model.addAttribute("onlineCount",onlineCount);
        return "chat";
    }
}
