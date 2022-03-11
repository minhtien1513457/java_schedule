package com.tienpm.scheduler.service;

import com.tienpm.scheduler.entity.ScheduleTaskEntity;
import com.tienpm.scheduler.request.ScheduleTaskUpdatedRequest;

public interface ScheduleTaskService {
    ScheduleTaskEntity updateByName(String name, ScheduleTaskUpdatedRequest updatedRequest);
}
