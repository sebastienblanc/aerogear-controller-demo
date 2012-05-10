package org.jboss.aerogear.controller.demo.idm.annotation;

import org.apache.deltaspike.security.api.authorization.annotation.SecurityBindingType;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(value = RUNTIME)
@Target({TYPE, METHOD})

@Documented

//cdi annotations
@SecurityBindingType
public @interface Protected {
}
