package com.pjcraig.controller;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * This class registers each endpoint class associated with this RESTful web service.
 * @author pjcraig
 */
@ApplicationPath("/api")
public class UserCommandApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        HashSet classes = new HashSet<Class<?>>();
        classes.add(UserService.class );
        return classes;
    }
}