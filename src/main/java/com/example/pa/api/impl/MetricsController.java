package com.example.pa.api.impl;

import com.example.pa.api.MetricsApi;
import com.google.common.util.concurrent.AtomicDouble;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    @Qualifier("c1")
    private Counter c1;

    @Autowired
    @Qualifier("g1")
    private AtomicDouble g1;

    @Autowired
    @Qualifier("ds1")
    private DistributionSummary ds1;

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
