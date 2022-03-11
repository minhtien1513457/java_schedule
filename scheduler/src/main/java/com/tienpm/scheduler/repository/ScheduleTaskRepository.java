package com.tienpm.scheduler.repository;

import com.tienpm.scheduler.entity.ScheduleTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleTaskRepository extends JpaRepository<ScheduleTaskEntity, String> {
    ScheduleTaskEntity findByName(String name);
}
