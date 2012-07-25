package org.jboss.aerogear.controller.demo;

import org.jboss.aerogear.controller.demo.model.UserLogin;

import javax.ejb.Stateful;

@Stateful
public class Login {

    //@Inject
    //private AuthenticatorManager authenticatorManager;

    public void index() {
        System.out.println("hello from security");
    }

    public UserLogin welcome(UserLogin userLogin) {

        //authenticatorManager.login(userLogin.getUsername(), userLogin.getPassword());
        return userLogin;
    }

}
