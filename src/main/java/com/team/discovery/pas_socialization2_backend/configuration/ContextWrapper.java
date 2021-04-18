package com.team.discovery.pas_socialization2_backend.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ContextWrapper {

    private static ApplicationContext applicationContext;

    public ContextWrapper(final ApplicationContext applicationContext) {

        ContextWrapper.applicationContext = applicationContext;
    }

    public static ApplicationContext getContext(){
        if (applicationContext == null){
            throw new IllegalStateException("Spring context available only after initialization");
        }
        return applicationContext;
    }
}
