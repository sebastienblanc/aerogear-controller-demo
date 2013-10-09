package org.jboss.aerogear.jaxrs.demo.service;

import org.jboss.aerogear.security.auth.AuthenticationManager;
import org.jboss.aerogear.security.authz.IdentityManagement;
import org.picketlink.idm.model.basic.User;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/admin")
public class AdminEndpoint {

    public static final String DEFAULT_ROLE = "simple";

    @Inject
    private IdentityManagement configuration;

    @Inject
    private AuthenticationManager authenticationManager;

    public List index() {
        return configuration.findAllByRole("simple");
    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<User> create(User user, String password) {
        configuration.create(user, password);
        configuration.grant(DEFAULT_ROLE).to(user.getLoginName());
        return configuration.findAllByRole("simple");
    }

    @DELETE
    @Path("/show/remove")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<User> remove(String username) {
        configuration.remove(username);
        return configuration.findAllByRole("simple");
    }

    @GET
    @Path("/show/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User show(@PathParam("username") String username) {
        return (User) configuration.findByUsername(username);
    }
}
