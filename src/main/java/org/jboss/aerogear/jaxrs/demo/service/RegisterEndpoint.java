package org.jboss.aerogear.jaxrs.demo.service;

import org.jboss.aerogear.security.auth.AuthenticationManager;
import org.jboss.aerogear.security.authz.IdentityManagement;
import org.picketlink.idm.model.basic.User;

import javax.inject.Inject;
import javax.ws.rs.Path;

@Path("/register")
public class RegisterEndpoint {

    public static final String DEFAULT_ROLE = "simple";

    @Inject
    private IdentityManagement configuration;

    @Inject
    private AuthenticationManager authenticationManager;

    public User register(User user, String password) {
        configuration.create(user, password);
        configuration.grant(DEFAULT_ROLE).to(user.getLoginName());
        authenticationManager.login(user, password);
        return user;
    }
}
