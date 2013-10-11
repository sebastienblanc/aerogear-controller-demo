package org.jboss.aerogear.jaxrs.demo.service;

import org.jboss.aerogear.jaxrs.demo.model.SimpleUser;
import org.jboss.aerogear.security.auth.LoggedUser;
import org.jboss.aerogear.security.auth.Secret;
import org.jboss.aerogear.security.otp.Totp;
import org.picketlink.idm.model.basic.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

@Stateless
@Path("/auth")
public class OtpEndpoint {

    @Inject
    @Secret
    private Instance<String> secret;

    @Inject
    @LoggedUser
    private Instance<String> loggedInUserName;

    private static final Logger LOGGER = Logger.getLogger(OtpEndpoint.class.getSimpleName());

    @GET
    @Path("/otp/secret")
    @Produces(MediaType.APPLICATION_JSON)
    public SimpleUser secret() {
       SimpleUser simpleUser = new SimpleUser();
       String otpSecret =  new Totp(secret.get()).uri(loggedInUserName.get());
       simpleUser.setUri(otpSecret);
       return simpleUser;
    }

    @POST
    @Path("/otp")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SimpleUser otp(SimpleUser simpleUser) {

        Totp totp = new Totp(secret.get());
        boolean result = totp.verify(simpleUser.getSecret());

        if (!result)
            throw new RuntimeException("Invalid OTP");

        return simpleUser;
    }
}
