package com.tienpm.scheduler.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ThreadPoolConfig {

    @Value("${threads.pool.core-pool-size}")
    private int corePoolSize;

    @Value("${threads.pool.max-pool-size}")
    private int maxPoolSize;

    @Value("${threads.pool.queue-capacity}")
    private int queueCapacity;


    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        return new ThreadPoolExecutor(corePoolSize,
                maxPoolSize,
                5, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queueCapacity));
    }
}
