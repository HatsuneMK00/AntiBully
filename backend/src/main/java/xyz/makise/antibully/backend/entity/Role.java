package xyz.makise.antibully.backend.entity;

import java.io.Serializable;

public class Role implements Serializable {
    int roleId;
    String name;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
