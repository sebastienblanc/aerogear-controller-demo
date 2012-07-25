package org.jboss.aerogear.controller.demo.model;

import org.jboss.aerogear.security.idm.util.MessageDigestUtil;

public class UserLogin {

    private String username;
    private String password;
    private String role;

    public UserLogin(String username, String password) {
        this.username = username;
        this.password = MessageDigestUtil.createDigestPassword(password);
    }

    public UserLogin(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
