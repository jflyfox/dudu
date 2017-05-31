package com.jflyfox.dudu.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 测试task
 * <p>
 * Created by flyfox 369191470@qq.com on 2017/5/18.
 */
@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public final static long ONE_MINUTE = 60 * 1000;

    @Scheduled(fixedDelay = ONE_MINUTE)
    public void fixedDelayJob() {
        log.info(dateFormat.format(new Date()) + " >>fixedDelay执行....");
    }

    @Scheduled(fixedRate = ONE_MINUTE)
    public void fixedRateJob() {
        log.info(dateFormat.format(new Date()) + " >>fixedRate执行....");
    }

    @Scheduled(cron = "0 15 3 * * ?")
    public void cronJob() {
        log.info(dateFormat.format(new Date()) + " >>cron执行....");
    }
}
