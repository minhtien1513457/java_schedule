CREATE TABLE shedlock
(
    name       VARCHAR(64),
    lock_until TIMESTAMP(3) NULL,
    locked_at  TIMESTAMP(3) NULL,
    locked_by  VARCHAR(255),
    PRIMARY KEY (name)
)

CREATE TABLE `schedule_task`
(
    `name`     varchar(50) NOT NULL,
    `cron`     varchar(50) DEFAULT NULL,
    `last_run` datetime    DEFAULT NULL,
    `enable`   tinyint(4) NOT NULL DEFAULT '0',
    PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

INSERT INTO schedule.schedule_task (name,cron,last_run,enable) VALUES
	 ('HelloWorldSchedule','0/10 * * ? * *',NULL,1);