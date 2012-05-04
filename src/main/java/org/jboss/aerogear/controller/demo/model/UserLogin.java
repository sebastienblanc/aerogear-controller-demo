package org.jboss.aerogear.controller.demo.model;

import org.jboss.aerogear.security.idm.authentication.AuthInfo;

public class UserLogin implements AuthInfo {

    private String username;
    private String password;
    private String role;

    public UserLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserLogin(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public Object getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    //TODO must to be fixed
    @Override
    public String getRole() {
        //return "customer";
        return role;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
    }
}
