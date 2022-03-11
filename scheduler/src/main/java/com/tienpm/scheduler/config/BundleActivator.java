package com.tienpm.scheduler.config;

import com.tienpm.scheduler.helper.ServiceRef;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BundleActivator {

    @Bean
    ServiceRef serviceRef() {
        return new ServiceRef();
    }
}
