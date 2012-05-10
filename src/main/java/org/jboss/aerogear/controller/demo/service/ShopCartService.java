package org.jboss.aerogear.controller.demo.service;

import org.apache.deltaspike.security.api.authorization.annotation.Secured;
import org.jboss.aerogear.controller.demo.idm.authorization.CheckAccessPermission;
import org.jboss.aerogear.controller.demo.model.Car;

import javax.enterprise.context.RequestScoped;

@RequestScoped
@Secured(CheckAccessPermission.class)
public class ShopCartService {

    //TODO replace with DS
    //@Protected(role = "customer")
    //@Protected
    public Car add(Car car) {
        System.out.println("car: " + car.getBrand());
        return car;
    }
}