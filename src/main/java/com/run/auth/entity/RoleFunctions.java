package com.run.auth.entity;

import com.run.auth.common.BaseEntity;

import java.io.Serializable;

/**
 * Created by dello on 2016/7/27.
 */
public class RoleFunctions extends BaseEntity implements Serializable {
    private Long Id;
    private Long roleId;
    private Long functionId;
    private int status;

    public RoleFunctions() {
    }

    public RoleFunctions(Long id, Long roleId, Long functionId, int status) {
        Id = id;
        this.roleId = roleId;
        this.functionId = functionId;
        this.status = status;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
