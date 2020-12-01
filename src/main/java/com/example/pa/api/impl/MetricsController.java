package com.example.pa.api.impl;

import com.example.pa.api.MetricsApi;
import com.google.common.util.concurrent.AtomicDouble;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

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
    private final AtomicDouble g1;
    private final DistributionSummary ds1;

    public MetricsController(final MeterRegistry registry) {
        c1 = Counter.builder("custom_c1")
                .description("c1 desc")
                .tag("metric", "counter")
                .register(registry);

        g1 = new AtomicDouble(0);
        Gauge.builder("custom_g1", g1, AtomicDouble::get)
                .description("g1 desc")
                .tag("metric", "gauge")
                .register(registry);

        ds1 = DistributionSummary.builder("custom_ds1")
                .description("ds1 desc")
                .tag("metric", "summary")
                .minimumExpectedValue(1D)
                .maximumExpectedValue(10D)
                .publishPercentiles(0.5, 0.75, 0.9)
                .register(registry);
    }

    @Override
    @GetMapping("/counter")
    public String counter() {
        c1.increment();
        return "custom_c1_total: " + c1.count();
    }

    @Override
    @GetMapping("/gauge")
    public String gauge() {
        double speed = ThreadLocalRandom.current()
                .nextDouble(120D);
        g1.getAndSet(speed);
        return "custom_g1: " + g1.get();
    }

    @Override
    @GetMapping("/summary")
    public String summary() {
        double speed = ThreadLocalRandom.current()
                .nextDouble(10D);
        ds1.record(speed);
        return "custom_ds1_max: " + ds1.max();
    }
}
