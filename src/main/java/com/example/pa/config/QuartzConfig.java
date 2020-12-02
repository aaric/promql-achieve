package com.example.pa.config;

import com.google.common.util.concurrent.AtomicDouble;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("c1")
    private Counter c1;

    @Autowired
    @Qualifier("g1")
    private AtomicDouble g1;

    @Autowired
    @Qualifier("ds1")
    private DistributionSummary ds1;

    @Scheduled(cron = "*/1 * * * * ?")
    void fakeMetricsCounter() {
        double sum = ThreadLocalRandom.current()
                .nextDouble(10D);
        c1.increment(sum);
    }

    @Scheduled(cron = "0/2 * * * * ?")
    void fakeMetricsGauge() {
        double speed = ThreadLocalRandom.current()
                .nextDouble(120D);
        g1.getAndSet(speed);
    }

    @Scheduled(cron = "0/3 * * * * ?")
    void fakeMetricsSummary() {
        double speed = ThreadLocalRandom.current()
                .nextDouble(10D);
        ds1.record(speed);
    }
}
