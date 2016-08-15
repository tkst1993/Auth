package com.run.auth.entity;

import com.run.auth.common.BaseEntity;

import java.io.Serializable;

/**
 * Created by dello on 2016/7/27.
 */
public class UserRole extends BaseEntity implements Serializable {
    private Long id;
    private Long userId;
    private Long roleId;

    public UserRole(Long id, Long userId, Long roleId) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
    }

    public UserRole() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}