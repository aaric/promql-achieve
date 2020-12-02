package com.example.pa.config;

import com.google.common.util.concurrent.AtomicDouble;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 监控配置
 *
 * @author Aaric, created on 2020-12-01T13:46.
 * @version 0.5.0-SNAPSHOT
 */
@Configuration
public class MetricsConfig {

    @Bean
    MeterRegistryCustomizer<MeterRegistry> meterRegistryCustomizer() {
        return registry -> registry.config().commonTags("common", "metrics");
    }

    @Bean("c1")
    Counter c1(final MeterRegistry registry) {
        return Counter.builder("custom_c1")
                .description("c1 desc")
                .tag("metric", "counter")
                .register(registry);
    }

    @Bean("g1")
    AtomicDouble g1(final MeterRegistry registry) {
        AtomicDouble g1 = new AtomicDouble(0);
        Gauge.builder("custom_g1", g1, AtomicDouble::get)
                .description("g1 desc")
                .tag("metric", "gauge")
                .register(registry);
        return g1;
    }

    @Bean("ds1")
    DistributionSummary ds1(final MeterRegistry registry) {
        return DistributionSummary.builder("custom_ds1")
                .description("ds1 desc")
                .tag("metric", "summary")
                .minimumExpectedValue(1D)
                .maximumExpectedValue(10D)
                .publishPercentiles(0.5, 0.75, 0.9)
                .register(registry);
    }
}
