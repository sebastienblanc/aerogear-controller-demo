package org.jboss.aerogear.controller.demo.service;

import org.jboss.aerogear.controller.demo.model.Car;
import org.jboss.aerogear.security.idm.authorization.Protected;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ShopCartService {

    @Protected(role = "customer")
    public Car add(Car car) {
        System.out.println("car: " + car.getBrand());
        return car;
    }
}