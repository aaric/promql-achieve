package com.example.pa.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * CounterTests（计数器）
 *
 * @author Aaric, created on 2020-12-01T10:48.
 * @version 0.5.0-SNAPSHOT
 */
@Slf4j
public class CounterTests {

    private SimpleMeterRegistry registry = new SimpleMeterRegistry();

    @Test
    public void testCounter() {
        // create
        Counter c1 = registry.counter("c1");
        c1.increment(2.0);

        Counter c2 = Counter.builder("c2")
                .description("c2 desc")
                .tag("metric", "counter")
                .register(registry);
        c2.increment();

        // result
        log.info("c1 -> count: {}, measure: {}", c1.count(), c1.measure());
        log.info("c2 -> count: {}, measure: {}", c2.count(), c2.measure());

        // assert
        Assertions.assertEquals(2.0, c1.count());
        Assertions.assertEquals(1.0, c2.count());
    }
}
