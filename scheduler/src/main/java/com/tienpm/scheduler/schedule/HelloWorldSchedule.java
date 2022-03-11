package com.tienpm.scheduler.schedule;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldSchedule extends ScheduleTask {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void run() {
        logger.info("Start HelloWorldSchedule");
        try {
            this.executor();
        } catch (Exception ex) {
            logger.error("HelloWorldSchedule error: " + ex.getMessage());
        }
        logger.info("End HelloWorldSchedule");
    }

    private void executor() {
        System.out.println("Hello world");
    }
}
