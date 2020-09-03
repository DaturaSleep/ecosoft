package com.vmaksym.ecosoft.entities;

public enum UserRole {
    ADMIN("ADMIN"), TEACHER("TEACHER"), PUPIL("PUPIL");

    public String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }
}
