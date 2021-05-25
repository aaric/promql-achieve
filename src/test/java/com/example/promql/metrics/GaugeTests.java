package com.example.promql.metrics;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * GaugeTests（仪表盘）
 *
 * @author Aaric, created on 2020-12-01T10:48.
 * @version 0.5.0-SNAPSHOT
 */
@Slf4j
public class GaugeTests {

    private SimpleMeterRegistry registry = new SimpleMeterRegistry();

    @Test
    public void testGauge() {
        // create
        AtomicInteger g1 = registry.gauge("g1", new AtomicInteger(0));
        g1.set(1);

        AtomicInteger g2 = new AtomicInteger(0);
        Gauge gauge2 = Gauge.builder("g2", g2, AtomicInteger::get)
                .description("g2 desc")
                .tag("metric", "gauge")
                .register(registry);
        g2.addAndGet(2);

        List<String> list = registry.gaugeCollectionSize("g3", Collections.emptyList(), new ArrayList<>());
        list.add("a");

        Map<String, String> map = registry.gaugeMapSize("g4", Collections.emptyList(), new HashMap<>());
        map.put("a", "a");

        // result
        log.info("g2 -> value: {}, measure: {}", gauge2.value(), gauge2.measure());

        // assert
        Assertions.assertEquals(2.0, gauge2.value());
    }
}
