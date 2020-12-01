package com.example.pa.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 定时调度配置
 *
 * @author Aaric, created on 2020-12-01T17:55.
 * @version 0.7.0-SNAPSHOT
 */
@Slf4j
@EnableScheduling
@Configuration
public class QuartzConfig {

    @Autowired
    MeterRegistry registry;

    @Scheduled(cron = "0/5 * * * * ?")
    void fakeMetricsCounter() {
        double sum = ThreadLocalRandom.current()
                .nextDouble(10D);
        Counter c1 = registry.get("custom_c1").counter();
        c1.increment(sum);
    }
}
