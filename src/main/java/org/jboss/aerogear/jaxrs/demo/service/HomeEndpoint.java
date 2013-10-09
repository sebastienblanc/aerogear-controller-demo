package org.jboss.aerogear.jaxrs.demo.service;

import org.jboss.aerogear.jaxrs.demo.model.Car;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/delorean")
public class HomeEndpoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Car anotherPage() {
        return new Car("silver", "delorean");
    }
}
