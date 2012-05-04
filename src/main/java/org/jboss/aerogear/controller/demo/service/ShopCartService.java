package org.jboss.aerogear.controller.demo.service;

import org.jboss.aerogear.security.idm.authorization.Protected;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ShopCartService {

    @Protected(role = "customer")
    public Long getUser(long id) {
        System.out.println("User id: " + id);
        return id;
    }
}