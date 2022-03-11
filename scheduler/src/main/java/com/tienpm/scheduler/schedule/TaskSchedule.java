package com.tienpm.scheduler.schedule;

import com.tienpm.scheduler.entity.ScheduleTaskEntity;
import com.tienpm.scheduler.helper.ScheduledTaskHelper;
import com.tienpm.scheduler.repository.ScheduleTaskRepository;
import com.tienpm.scheduler.request.ScheduleTaskUpdatedRequest;
import com.tienpm.scheduler.service.ScheduleTaskService;
import lombok.RequiredArgsConstructor;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

@Component
@RequiredArgsConstructor
public class TaskSchedule {
    private static final Logger LOG = LogManager.getLogger();
    private final ThreadPoolExecutor threadPoolExecutor;
    private final ScheduleTaskRepository scheduleTaskRepository;
    private final ScheduleTaskService scheduleTaskService;

    @Value("${schedule.task.cron}")
    private String SCHEDULE_TASK_SCANNER_CRON;
    @Value("${schedule.task.scanner.interval.milisecond}")
    private long SCHEDULE_TASK_SCANNER_INTERVAL_MILLISECONDS;



    @Scheduled(cron = "${schedule.task.cron}")
    @SchedulerLock(name = "BillingGatewayTask",
            lockAtMostFor = "${schedule.task.lock.most}")
    public void handleTask() {
        LOG.info("Start to handle ..." + new Date());
        List<ScheduleTaskEntity> scheduleTaskEntities = this.scheduleTaskRepository.findAll();
        for (ScheduleTaskEntity task : scheduleTaskEntities) {
            try {
                threadPoolExecutor.execute(() -> this.run(task));
            } catch (Exception e) {
                LOG.error(String.format("Scan task %s is failed", task.getName()), e);
            }
        }
        LOG.info("End to handle ..." + new Date());
    }

    private void run(ScheduleTaskEntity task) {
        try {
            if (isEnabledTask(task)) {
                ScheduledTaskHelper.getTask(task.getName()).run();
                ScheduleTaskUpdatedRequest scheduleTaskUpdatedRequest = ScheduleTaskUpdatedRequest.builder()
                        .lastRun(new Date())
                        .build();
                this.scheduleTaskService.updateByName(task.getName(), scheduleTaskUpdatedRequest);
            }
        } catch (Exception e) {
            LOG.error(String.format("Scan task %s is failed", task.getName()), e);
        }
    }

    private boolean isEnabledTask(ScheduleTaskEntity task) {
        try {
            Date now = getScanTime();
            Date previous = new Date(now.getTime() - SCHEDULE_TASK_SCANNER_INTERVAL_MILLISECONDS);
            CronExpression cronExpression = new CronExpression(task.getCron());
            return task.isEnable() && !cronExpression.getNextValidTimeAfter(previous).after(now);
        } catch (Exception e) {
            LOG.error(e);
            return false;
        }
    }

    private Date getScanTime() throws ParseException {
        Date now = new Date();
        Date previous = new Date(now.getTime() - SCHEDULE_TASK_SCANNER_INTERVAL_MILLISECONDS);
        CronExpression cronExpression = new CronExpression(SCHEDULE_TASK_SCANNER_CRON);
        return cronExpression.getNextValidTimeAfter(previous);
    }


}
