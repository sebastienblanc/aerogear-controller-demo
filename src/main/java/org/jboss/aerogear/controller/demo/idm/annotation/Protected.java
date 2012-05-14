package org.jboss.aerogear.controller.demo.idm.annotation;

import org.apache.deltaspike.security.api.authorization.annotation.SecurityBindingType;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@SecurityBindingType
public @interface Protected {
}
