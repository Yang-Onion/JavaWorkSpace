package com.yangonion.shiro.bean;

import java.io.Serializable;
import java.util.Date;

public class ShiroUser  implements Serializable {

    private static  final long servialVersionId =-339516038496531943L;

    private  long id;
    private  String name;
    private  String password;
    private  String status;
    private Date crateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
    }
}
