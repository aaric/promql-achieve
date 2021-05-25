package com.example.promql.metrics;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * SummaryTests（摘要）
 *
 * @author Aaric, created on 2020-12-01T10:54.
 * @version 0.5.0-SNAPSHOT
 */
@Slf4j
public class SummaryTests {

    private SimpleMeterRegistry registry = new SimpleMeterRegistry();

    @Test
    public void testSummary() {
        // create
        DistributionSummary ds1 = DistributionSummary.builder("ds1")
                .description("ds1 desc")
                .tag("metric", "summary")
                .minimumExpectedValue(1D)
                .maximumExpectedValue(10D)
                .publishPercentiles(0.5, 0.75, 0.9)
                .register(registry);
        ds1.record(1);
        ds1.record(1.3);
        ds1.record(2.4);
        ds1.record(3.5);
        ds1.record(4.1);

        // result
        log.info("{}", ds1.takeSnapshot());
        log.info("ds1 -> count: {}, measure: {}, totalAmount: {}, mean: {}, max: {}",
                ds1.count(), ds1.measure(), ds1.totalAmount(), ds1.mean(), ds1.max());

    }
}
