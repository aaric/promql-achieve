package com.example.promql.config;

import com.google.common.util.concurrent.AtomicDouble;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    @Qualifier("t1")
    private Timer t1;

    @Scheduled(cron = "*/1 * * * * ?")
    void fakeMetricsCounter() {
        double sum = ThreadLocalRandom.current()
                .nextDouble(10D);
        c1.increment(sum);

        log.info("c1 updated.");
    }

    @Scheduled(cron = "*/2 * * * * ?")
    void fakeMetricsGauge() {
        double speed = ThreadLocalRandom.current()
                .nextDouble(120D);
        g1.getAndSet(speed);

        log.info("g1 updated.");
    }

    @Scheduled(cron = "*/3 * * * * ?")
    void fakeMetricsSummary() {
        double speed = ThreadLocalRandom.current()
                .nextDouble(10D);
        ds1.record(speed);

        log.info("ds1 updated.");
    }

    @Scheduled(cron = "*/4 * * * * ?")
    void fakeMetricsTimer() {
        t1.record(() -> {
            try {
                int seconds = ThreadLocalRandom.current().nextInt(3);
                TimeUnit.SECONDS.sleep(seconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        log.info("t1 updated.");
    }
}
