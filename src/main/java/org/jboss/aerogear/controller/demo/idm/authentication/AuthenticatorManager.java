package org.jboss.aerogear.controller.demo.idm.authentication;

import org.apache.deltaspike.security.api.Identity;
import org.apache.deltaspike.security.api.User;
import org.apache.deltaspike.security.api.credential.Credential;
import org.apache.deltaspike.security.api.credential.LoginCredential;
import org.apache.deltaspike.security.spi.authentication.BaseAuthenticator;
import org.jboss.aerogear.controller.demo.idm.persistence.UserRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class AuthenticatorManager extends BaseAuthenticator {

    @Inject
    private LoginCredential loginCredential;

    @Inject
    private Identity identity;

    @Inject
    private UserRegistry userRegistry;

    private User user;

    private static final Logger log = LoggerFactory.getLogger(AuthenticatorManager.class);

    @Override
    public void authenticate() {
        Object user = userRegistry.findBy(this.loginCredential.getUserId());

        log.info("================== User: " + user);

        if (user != null) {
            setStatus(AuthenticationStatus.SUCCESS);
            this.user = new User(this.loginCredential.getUserId());
            return;
        }

        setStatus(AuthenticationStatus.FAILURE);
    }

    @Override
    public User getUser() {
        return this.user;
    }

    public void login(String userName, final String password) {

        Object user = userRegistry.findBy(userName);

        if (user != null) {
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
    }

    public void logout() {
        this.identity.logout();
    }
}
