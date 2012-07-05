package org.jboss.aerogear.controller.demo;

import org.jboss.aerogear.controller.RequestMethod;
import org.jboss.aerogear.controller.demo.model.Car;
import org.jboss.aerogear.controller.router.AbstractRoutingModule;

public class Routes extends AbstractRoutingModule {

    @Override
    public void configuration() {
        route()
                .from("/")
                .on(RequestMethod.GET)
                .to(Home.class).index();
        route()
                .from("/delorean")
                .on(RequestMethod.GET)
                .to(Home.class).anotherPage();
        route()
                .from("/cars")
                .on(RequestMethod.POST)
                .to(Home.class).save(param(Car.class));
        route()
                .from("/login")
                .on(RequestMethod.GET)
                .to(Login.class).index();
        route()
                .from("/welcome")
                .on(RequestMethod.POST)
                .to(Login.class).welcome(param(UserLogin.class));
        route()
                .from("/shop")
                .on(RequestMethod.GET)
                .to(Shop.class).index();
    }
}
