package org.jboss.aerogear.controller.demo;

import org.jboss.aerogear.controller.demo.model.Car;
import org.jboss.aerogear.controller.demo.service.ShopCartService;

//@Stateful
public class Shop {

    //@Inject
    private ShopCartService shopCartService;

    //TODO replace with DS
    //@CustomSecurityBinding(role = "customer")
    public void index() {
        System.out.println("hello from shop");
    }

    public Car save(Car car) {
        return shopCartService.add(car);
    }
}
