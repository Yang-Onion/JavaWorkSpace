package com.yangonion.shiro.bean;

import java.io.Serializable;

public class ShiroPermission implements Serializable {
    private static final long serialVersionUID = 7883095880684240946L;

    private  int id;
    private  String url;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
