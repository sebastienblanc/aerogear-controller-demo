/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.aerogear.controller.demo;

import org.jboss.aerogear.security.auth.AuthenticationManager;
import org.jboss.aerogear.security.auth.LoggedUser;
import org.jboss.aerogear.security.authz.IdentityManagement;
import org.jboss.aerogear.security.model.AeroGearUser;

import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Stateless
public class Admin {


    public static final String DEFAULT_ROLE = "simple";

    @Inject
    private IdentityManagement configuration;

    @Inject
    private AuthenticationManager authenticationManager;

    @Inject
    @LoggedUser
    private Instance<String> loggedInUserName;

    public List index() {
        return listUsers();
    }

    public List register(AeroGearUser user){
        configuration.create(user);
        configuration.grant(DEFAULT_ROLE).to(user);
        List userList = listUsers();
        return userList;
    }

    public List remove(AeroGearUser aeroGearUser) {
        configuration.remove(aeroGearUser);
        return listUsers();
    }

    public AeroGearUser show(String id){
        return configuration.get(id);
    }

    /**
     * List all the registered users excepted the logged in user
     *
     */
    private List listUsers() {
        List users = configuration.findAllByRole("simple");
        AeroGearUser loggedInUser = configuration.get(loggedInUserName.get());
        List filteredList = new ArrayList();
        Iterator iterator = users.iterator();
        while(iterator.hasNext()){
            AeroGearUser current = (AeroGearUser)iterator.next();
            if(!current.getUsername().equals(loggedInUser.getUsername())){
                filteredList.add(current);
            }
        }
        return filteredList;
    }
}
