package org.jboss.aerogear.jaxrs.demo.service;

import org.jboss.aerogear.jaxrs.demo.model.SimpleUser;
import org.jboss.aerogear.security.auth.AuthenticationManager;
import org.picketlink.idm.model.basic.User;

import javax.inject.Inject;
import javax.ws.rs.*;
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
    public SimpleUser login(SimpleUser simpleUser) {
        User user = new User(simpleUser.getLoginName());
        authenticationManager.login(user, simpleUser.getPassword());
        return simpleUser;
    }

    @POST
    @Path("/logout")
    public void logout() {
        LOGGER.info("User logout!");
        authenticationManager.logout();
    }
}
