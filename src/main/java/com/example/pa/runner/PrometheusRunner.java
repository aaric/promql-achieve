package com.example.pa.runner;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 初始化Prometheus监控配置
 *
 * @author Aaric, created on 2020-11-17T11:02.
 * @version 0.3.0-SNAPSHOT
 */
@Order(1)
@Component
public class PrometheusRunner implements CommandLineRunner {

    /**
     * http get counter
     */
    private static Counter httpGetCounter;

    /**
     * http post counter
     */
    private static Counter httpPostCounter;

    /**
     * http counter
     */
    private static Counter httpCounter;

    @Autowired
    private MeterRegistry registry;

    @Override
    public void run(String... args) throws Exception {
        httpGetCounter = registry.counter("http_get_counter", "method", "get");
        httpPostCounter = registry.counter("http_post_counter", "method", "post");
        httpCounter = registry.counter("http_counter", "method", "all");
    }

    public static Counter getHttpGetCounter() {
        return httpGetCounter;
    }

    public static Counter getHttpPostCounter() {
        return httpPostCounter;
    }

    public static Counter getHttpCounter() {
        return httpCounter;
    }
}
