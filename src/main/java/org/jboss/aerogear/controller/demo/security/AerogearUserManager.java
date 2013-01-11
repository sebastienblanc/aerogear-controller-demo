/***
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.

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

package org.jboss.aerogear.controller.demo.security;

import org.jboss.aerogear.controller.demo.util.Converter;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.model.User;
import org.picketlink.idm.query.IdentityQuery;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sebastien
 * Date: 12/13/12
 * Time: 9:12 AM
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class AerogearUserManager {

    @Inject
    private IdentityManager identityManager;


    public List getAllSimpleUsers(){
        List aerogearUsers = new ArrayList();
        IdentityQuery<User> query = identityManager.createQuery(User.class);
        query.setParameter(User.HAS_ROLE, new String[] { "simple" });
        List<User> result = query.getResultList();
        for(User user:result){
          aerogearUsers.add(Converter.convertToAerogearUser(user));
        }

        return aerogearUsers;

    }

    public void removeUser(String name){
       User user = identityManager.getUser(name);
        if(user != null){
          identityManager.remove(user);
         }

    }

    public User showUser(String name){
        return identityManager.getUser(name);
    }
}
