package org.jboss.aerogear.controller.demo.idm.authentication;

import org.apache.deltaspike.security.api.Identity;
import org.apache.deltaspike.security.api.User;
import org.apache.deltaspike.security.api.credential.Credential;
import org.apache.deltaspike.security.api.credential.LoginCredential;
import org.apache.deltaspike.security.spi.authentication.BaseAuthenticator;
import org.jboss.aerogear.controller.demo.idm.fixture.InMemoryUserStorage;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class AuthenticatorManager extends BaseAuthenticator {

    @Inject
    private LoginCredential loginCredential;

    @Inject
    private Identity identity;

    private User user;

    @Override
    public void authenticate() {
//        String password = InMemoryUserStorage.getPassword(this.loginCredential.getUserId());
        String password = InMemoryUserStorage.getPassword("test1");

        //if (password != null && password.equals(this.loginCredential.getCredential().getValue())) {
        if (password != null && password.equals("test")) {
            setStatus(AuthenticationStatus.SUCCESS);
            //this.user = new User(this.loginCredential.getUserId());
            this.user = new User("test");
            return;
        }
    }

    @Override
    public User getUser() {
        return this.user;
    }

    public void login(String userName, final String password) {
        this.loginCredential.setUserId(userName);
        //TODO discuss #setSecurityToken
        this.loginCredential.setCredential(new Credential<String>() {
            @Override
            public String getValue() {
                return password;
            }
        });

        this.identity.login();
    }

    public void logout() {
        this.identity.logout();
    }
}
