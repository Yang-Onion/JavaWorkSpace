package com.yangonion.websocket.component;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Yang-Onion Github:https://www.github/yang-onion
 * @version 创建时间：2018/11/22 17:24
 */
@Component
public class SocketSessionMap {
    private  final static ConcurrentMap<String,String> sessionMap = new ConcurrentHashMap<>();

    public synchronized  void registerSession(String userId,String sessionId){
        sessionMap.put(userId,sessionId);
    }

    public  synchronized  void removeSeesion(String userId){
        sessionMap.remove(userId);
    }

    public String getUserSeesionId(String userId){
        return  sessionMap.get(userId);
    }

    public Map<String,String> queryAllSession(){
        return  sessionMap;
    }

    public  int onlineCount(){
        return  sessionMap.size();
    }
}
