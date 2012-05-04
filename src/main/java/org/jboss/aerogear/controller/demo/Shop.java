package org.jboss.aerogear.controller.demo;

import org.jboss.aerogear.controller.demo.model.Car;
import org.jboss.aerogear.controller.demo.model.UserLogin;
import org.jboss.aerogear.controller.demo.service.ShopCartService;
import org.jboss.aerogear.security.idm.authorization.Protected;

import javax.ejb.Stateful;
import javax.inject.Inject;

@Stateful
public class Shop {

    @Inject
    private ShopCartService shopCartService;

    @Protected(role = "customer")
    public void index() {
        System.out.println("hello from shop");
    }

    public Car save(Car car) {
        return shopCartService.add(car);
    }
}
