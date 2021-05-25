package com.example.promql.api.impl;

import com.example.promql.api.MetricsApi;
import com.google.common.util.concurrent.AtomicDouble;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    /**
     * custom_c1_total{application="springboot",common="metrics",metric="counter",} 17.72959922644321
     */
    @Override
    @GetMapping("/counter")
    public String counter() {
//        c1.increment();
        return "custom_c1_total: " + c1.count();
    }

    /**
     * custom_g1{application="springboot",common="metrics",metric="gauge",} 80.24004896675703
     */
    @Override
    @GetMapping("/gauge")
    public String gauge() {
//        double speed = ThreadLocalRandom.current()
//                .nextDouble(120D);
//        g1.getAndSet(speed);
        return "custom_g1: " + g1.get();
    }

    /**
     * custom_ds1_max{application="springboot",common="metrics",metric="summary",} 7.1909231690242095
     *
     * custom_ds1{application="springboot",common="metrics",metric="summary",quantile="0.5",} 4.5
     * custom_ds1{application="springboot",common="metrics",metric="summary",quantile="0.75",} 7.0
     * custom_ds1{application="springboot",common="metrics",metric="summary",quantile="0.9",} 7.0
     *
     * custom_ds1_count{application="springboot",common="metrics",metric="summary",} 2.0
     * custom_ds1_sum{application="springboot",common="metrics",metric="summary",} 11.877071277423703
     */
    @Override
    @GetMapping("/summary")
    public String summary() {
//        double speed = ThreadLocalRandom.current()
//                .nextDouble(10D);
//        ds1.record(speed);
        return "custom_ds1_count: " + ds1.count();
    }

    /**
     * custom_t1_seconds_count{application="springboot",common="metrics",metric="timer",} 1.0
     * custom_t1_seconds_sum{application="springboot",common="metrics",metric="timer",} 1.0004827
     *
     * custom_t1_seconds_max{application="springboot",common="metrics",metric="timer",} 1.0004827
     */
    @Override
    @GetMapping("/timer")
    public String timer() {
        return "custom_t1_seconds_count: " + t1.count();
    }
}
