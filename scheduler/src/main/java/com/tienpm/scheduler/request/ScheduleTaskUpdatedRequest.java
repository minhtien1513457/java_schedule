package com.tienpm.scheduler.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleTaskUpdatedRequest {
    private Date lastRun;
    private String cron;
    private Boolean enable;
}
