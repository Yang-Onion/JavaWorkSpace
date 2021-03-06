package com.yangonion.shiro.bean;

import java.io.Serializable;

public class ShiroRole implements Serializable {
    private static final long serialVersionUID = 8077066908062062414L;
    private int id;
    private String name;
    private String memo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
