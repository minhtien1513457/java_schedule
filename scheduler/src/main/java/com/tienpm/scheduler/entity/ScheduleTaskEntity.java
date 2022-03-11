package com.tienpm.scheduler.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "schedule_task")
public class ScheduleTaskEntity {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "cron")
    private String cron;

    @Column(name = "last_run")
    private Date lastRun;

    @Column(name = "enable")
    private boolean enable;
}
