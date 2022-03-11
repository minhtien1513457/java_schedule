package com.tienpm.scheduler.service;

import com.tienpm.scheduler.entity.ScheduleTaskEntity;
import com.tienpm.scheduler.repository.ScheduleTaskRepository;
import com.tienpm.scheduler.request.ScheduleTaskUpdatedRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleTaskServiceImpl implements ScheduleTaskService {
    private final ScheduleTaskRepository scheduleTaskRepository;

    @Override
    public ScheduleTaskEntity updateByName(String name, ScheduleTaskUpdatedRequest updatedRequest) {
        ScheduleTaskEntity scheduleTaskEntity = this.scheduleTaskRepository.findByName(name);
        if (updatedRequest.getCron() != null) {
            scheduleTaskEntity.setCron(updatedRequest.getCron());
        }
        if (updatedRequest.getLastRun() != null) {
            scheduleTaskEntity.setLastRun(updatedRequest.getLastRun());
        }
        if (updatedRequest.getEnable() != null) {
            scheduleTaskEntity.setEnable(updatedRequest.getEnable());
        }
        return this.scheduleTaskRepository.save(scheduleTaskEntity);
    }
}
