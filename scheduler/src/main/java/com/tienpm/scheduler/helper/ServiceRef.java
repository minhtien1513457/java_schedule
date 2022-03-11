package com.tienpm.scheduler.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class ServiceRef {
    public static ServiceRef INSTANCE;

    public ServiceRef(){
        INSTANCE=this;
    }

    @Autowired
    public ApplicationContext context;
}
