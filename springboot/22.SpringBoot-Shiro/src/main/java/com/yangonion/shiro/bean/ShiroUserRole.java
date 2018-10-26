package com.yangonion.shiro.bean;

import java.io.Serializable;

public class ShiroUserRole implements Serializable {
    private static final long serialVersionUID = 590854334153733059L;

    private  int userid;
    private  int roleid;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }
}
