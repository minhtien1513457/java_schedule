package com.tienpm.scheduler.helper;

import com.tienpm.scheduler.schedule.ScheduleTask;

import java.beans.Introspector;

public class ScheduledTaskHelper {

    public static ScheduleTask getTask(String name) {
        return (ScheduleTask) ServiceRef.INSTANCE.context.getBean(Introspector.decapitalize(name));
    }
}
