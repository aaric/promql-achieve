package com.example.pa.api.impl;

import com.example.pa.api.PrometheusApi;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * 测试Java监控API接口实现
 *
 * @author Aaric, created on 2020-11-17T10:26.
 * @version 0.3.0-SNAPSHOT
 */
@RestController
@RequestMapping("/api/prometheus")
public class PrometheusController implements PrometheusApi {

    private Counter httpGetCounter;
    private Counter httpPostCounter;

    @Autowired
    private MeterRegistry meterRegistry;

    @PostConstruct
    private void init() {
        httpGetCounter = meterRegistry.counter("http_get_counter", "method", "get");
        httpPostCounter = meterRegistry.counter("http_post_counter", "method", "post");
    }

    @Override
    @GetMapping("/get")
    public String get() {
        // add
        httpGetCounter.increment();

        return "method: get - " + httpGetCounter.count();
    }

    @Override
    @PostMapping("/post")
    public String post() {
        // add
        httpPostCounter.increment();

        return "method: post - " + httpPostCounter.count();
    }
}
