package org.jboss.aerogear.jaxrs.demo.service;

import org.jboss.aerogear.security.auth.AuthenticationManager;
import org.picketlink.idm.model.basic.User;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

@Path("/auth")
public class LoginEndpoint {

    private static final Logger LOGGER = Logger.getLogger(LoginEndpoint.class.getSimpleName());

    @Inject
    private AuthenticationManager authenticationManager;

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User login(final User user, String password) {

        authenticationManager.login(user, password);
        return user;
    }

    @POST
    @Path("/logout")
    public void logout() {
        LOGGER.info("User logout!");
        authenticationManager.logout();
    }
}
