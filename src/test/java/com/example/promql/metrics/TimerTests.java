package com.example.promql.metrics;

import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * TimerTests（计时器）
 *
 * @author Aaric, created on 2020-12-01T11:28.
 * @version 0.5.0-SNAPSHOT
 */
@Slf4j
public class TimerTests {

    private SimpleMeterRegistry registry = new SimpleMeterRegistry();

    @Test
    public void testTimer() throws Exception {
        // create
        Timer t1 = registry.timer("t1");
        t1.record(() -> {
            try {
                // sleep 3s
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Timer t2 = Timer.builder("t2")
                .description("t2 desc")
                .tag("metric", "timer")
                .register(registry);

        t2.record(() -> {
            try {
                // sleep 2s
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        /*LongTaskTimer t3 = registry.more().longTaskTimer("t3");
        t3.record(() -> {
            try {
                // sleep 4s
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });*/

        // waiting...
        TimeUnit.SECONDS.sleep(3);

        // result
        log.info("t1 -> count: {}, measure: {}, totalTime: {}s, mean: {}s, max: {}s",
                t1.count(), t1.measure(), t1.totalTime(TimeUnit.SECONDS), t1.mean(TimeUnit.SECONDS), t1.max(TimeUnit.SECONDS));
        log.info("t2 -> count: {}, measure: {}, totalTime: {}s, mean: {}s, max: {}s",
                t2.count(), t2.measure(), t2.totalTime(TimeUnit.SECONDS), t2.mean(TimeUnit.SECONDS), t2.max(TimeUnit.SECONDS));
    }
}
