package org.jboss.aerogear.controller.demo.service;

import org.jboss.aerogear.controller.demo.idm.annotation.CustomSecurityBinding;
import org.jboss.aerogear.controller.demo.model.Car;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ShopCartService {

    @CustomSecurityBinding
    public Car add(Car car) {
        System.out.println("car: " + car.getBrand());
        return car;
    }
}