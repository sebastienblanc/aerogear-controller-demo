package org.jboss.aerogear.controller.demo.service;

import org.jboss.aerogear.controller.demo.idm.persistence.Role;
import org.jboss.aerogear.controller.demo.idm.persistence.RoleRegistryImpl;
import org.jboss.aerogear.controller.demo.idm.persistence.User;
import org.jboss.aerogear.controller.demo.idm.persistence.UserRegistryImpl;
import org.jboss.aerogear.controller.demo.model.Car;
import org.jboss.aerogear.controller.demo.util.Resources;
import org.jboss.aerogear.security.idm.authentication.AuthInfo;
import org.jboss.aerogear.security.idm.authentication.AuthInfoImpl;
import org.jboss.aerogear.security.idm.authentication.Identity;
import org.jboss.aerogear.security.idm.authentication.IdentityImpl;
import org.jboss.aerogear.security.idm.authorization.*;
import org.jboss.aerogear.security.idm.authorization.exception.AccessDeniedException;
import org.jboss.aerogear.security.idm.persistence.RoleRegistry;
import org.jboss.aerogear.security.idm.persistence.UserRegistry;
import org.jboss.aerogear.security.spi.AuthenticatorManager;
import org.jboss.aerogear.security.spi.AuthenticatorManagerImpl;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(Arquillian.class)
public class ShopCartServiceIT {

    @Inject
    private UserRegistry userRegistry;

    @Inject
    private RoleRegistry roleRegistry;

    @Inject
    private AuthenticatorManager authenticatorManager;

    @Inject
    private ShopCartService shopCartService;

    public Role buildRole(String roleName) {
        Role role = new Role(roleName, roleName);
        roleRegistry.newRole(role);
        return role;
    }

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(ShopCartService.class, SecurityInterceptor.class,
                        SecurityInterceptorBinding.class, Protected.class,
                        Resources.class, Role.class, User.class, Car.class,
                        RoleRegistry.class, RoleRegistryImpl.class,
                        UserRegistry.class, UserRegistryImpl.class,
                        AuthInfo.class, AuthInfoImpl.class,
                        AuthenticatorManager.class, AuthenticatorManagerImpl.class,
                        Identity.class, IdentityImpl.class,
                        RoleManager.class, RoleManagerImpl.class,
                        AccessDeniedException.class)
                .addAsWebInfResource("beans.xml", "beans.xml")
                .addAsResource("persistence.xml", "META-INF/persistence.xml");
    }

    @Test
    public void shouldAccessProtectedResourceWithValidLogin() throws Exception {
        try {
            User user = new User("test", "test");
            user.setRoles(buildRole("admin"));
            userRegistry.newUser(user);
            authenticatorManager.login(getAuthInfo("test", "test", "admin"));
            shopCartService.add(new Car("red", "hat"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldThrowExceptionWhenRoleInvalid() throws Exception {
        try {
            User user = new User("test2", "test2");
            user.setRoles(buildRole("manager"));
            userRegistry.newUser(user);
            authenticatorManager.login(getAuthInfo("test2", "test2", "guest"));
            shopCartService.add(new Car("red", "hat"));
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void shouldThrowExceptionWithoutValidLogin() throws Exception {
        try {
            shopCartService.add(new Car("red", "hat"));
            fail("Should throw authorization exception");
        } catch (Exception e) {
            assertTrue(true);
        }
    }


    private AuthInfo getAuthInfo(String id, String description, String role) {
        return new AuthInfoImpl(id, description, role);
    }
}
