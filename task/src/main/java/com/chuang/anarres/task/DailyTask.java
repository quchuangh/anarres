package com.chuang.anarres.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DailyTask {

    @Scheduled(cron = "0 */10 * * * ?")
    public void touch() {
        log.info("开始touch");

    }
}
