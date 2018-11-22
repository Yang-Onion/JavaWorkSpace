package com.yangonion.websocket.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

/**
 * @author Yang-Onion Github:https://www.github/yang-onion
 * @version 创建时间：2018/11/22 17:19
 */
@Component
public class STOMPConnectEventListener implements ApplicationListener<SessionConnectedEvent> {

    @Autowired
    private  SocketSessionMap socketSessionMap;

    @Override
    public void onApplicationEvent(SessionConnectedEvent event) {
        StompHeaderAccessor stompHeaderAccessor=StompHeaderAccessor.wrap(event.getMessage());
        String userId = stompHeaderAccessor.getFirstNativeHeader("id");
        String sessionId = stompHeaderAccessor.getSessionId();
        switch (stompHeaderAccessor.getCommand()){
            case CONNECT:
                System.out.println("上线"+userId+" "+sessionId);
                socketSessionMap.registerSession(userId,sessionId);
                break;
            case DISCONNECT:
                //System.out.println("下线"+userId);
               // socketSessionMap.removeSeesion(userId);
                break;
            case SUBSCRIBE:
                System.out.println("订阅");
                break;
            case SEND:
                System.out.println("发送");
                break;
            case UNSUBSCRIBE:
                System.out.println("取消订阅");
                break;
                default:
                    break;
        }
    }
}
