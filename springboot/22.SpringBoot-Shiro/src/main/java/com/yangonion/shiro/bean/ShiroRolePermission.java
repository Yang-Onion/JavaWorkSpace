package com.yangonion.shiro.bean;

import java.io.Serializable;

public class ShiroRolePermission implements Serializable {

    private static final long serialVersionUID = -749640821170772668L;
    private  int roleid;
    private  int permissionid;

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public int getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(int permissionid) {
        this.permissionid = permissionid;
    }
}
