package com.example.pa.api.impl;

import com.example.pa.api.MetricsApi;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MetricsController
 *
 * @author Aaric, created on 2020-12-01T13:50.
 * @version 0.5.0-SNAPSHOT
 */
@RestController
@RequestMapping("/api/metrics")
public class MetricsController implements MetricsApi {

    private final Counter c1;

    public MetricsController(final MeterRegistry registry) {
        c1 = Counter.builder("custom_c1")
                .description("c1 desc")
                .tag("metric", "counter")
                .register(registry);
    }

    @Override
    @GetMapping("/counter")
    public String counter() {
        c1.increment();
        return "custom_c1: " + c1.count();
    }
}
