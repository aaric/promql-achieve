package com.example.promql.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;

/**
 * AOP配置
 *
 * @author Aaric, created on 2020-11-17T11:19.
 * @version 0.3.0-SNAPSHOT
 */
@Slf4j
@Aspect
@Configuration
public class AopConfig {

    ThreadLocal<Long> start = new ThreadLocal<>();

    final Counter httpCounter;

    public AopConfig(final MeterRegistry registry) {
        httpCounter = Counter.builder("custom_http_counter")
                .description("http counter")
                .tag("metric", "counter")
                .register(registry);
    }

    @Pointcut("execution(public * com.example.pa.api.*.*(..))")
    void pointCut() {
    }

    @Before("pointCut()")
    void beforeApi() {
        // add
        httpCounter.increment();

        start.set(Instant.now().toEpochMilli());
    }

    @AfterReturning(pointcut = "pointCut()", returning = "returnVal")
    void afterApi(Object returnVal) {
        long end = Instant.now().toEpochMilli();

        log.info("aop -> {} - {}, {}", start.get(), end, end - start.get());
    }
}
