package org.jboss.aerogear.controller.demo;

import org.jboss.aerogear.controller.demo.model.UserLogin;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class Login {

    @Inject
    //TODO replace with DS
    //private AuthenticatorManager authenticatorManager;

    public void index() {
        System.out.println("hello from security");
    }

    public UserLogin welcome(UserLogin userLogin) {

        //authenticatorManager.login(userLogin);
        //TODO authentication exception should redirect to some error page
        return userLogin;
    }

}
