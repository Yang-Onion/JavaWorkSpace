package com.yangonion.websocket.model;

/**
 * @author Yang-Onion Github:https://www.github/yang-onion
 * @version 创建时间：2018/11/22 17:38
 */
public class ChatMessage {
    private  int fromId;
    private  String content;
    private  int toId;

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }
}
