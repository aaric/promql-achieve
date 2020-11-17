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
    public static Counter HTTP_GET_COUNTER;

    /**
     * http post counter
     */
    public static Counter HTTP_POST_COUNTER;

    /**
     * http counter
     */
    public static Counter HTTP_COUNTER;

    @Autowired
    private MeterRegistry registry;

    @Override
    public void run(String... args) throws Exception {
        HTTP_GET_COUNTER = registry.counter("http_get_counter", "method", "get");
        HTTP_POST_COUNTER = registry.counter("http_post_counter", "method", "post");
        HTTP_COUNTER = registry.counter("http_counter", "method", "all");
    }
}
