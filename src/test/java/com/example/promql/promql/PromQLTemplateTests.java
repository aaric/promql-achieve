package com.example.promql.promql;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

/**
 * PromQLApiUtilsTests
 *
 * @author Aaric, created on 2020-11-23T11:44.
 * @version 0.4.0-SNAPSHOT
 */
@Disabled
@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PromQLTemplateTests {

    @Autowired
    private RestTemplate restTemplate;

    private PromQLTemplate promQLTemplate;

    @BeforeEach
    public void setup() {
        promQLTemplate = new PromQLTemplate("http://gke-master:9090", restTemplate);
    }

    @Test
    public void testQuery() {
        long current = Instant.now().getEpochSecond();

        PromQLRequest request = new PromQLRequest("up{job=\"prometheus\"}", current);

        PromQLResponse<PromQLResultValue> response = promQLTemplate.query(request);

        Assertions.assertEquals("success", response.getStatus());
    }

    @Test
    public void testQueryRange() {
        long current = Instant.now().getEpochSecond();

        PromQLRangeRequest request = new PromQLRangeRequest("up{job=\"prometheus\"}");
        request.setStart(current - 60 * 5);
        request.setEnd(current);
        request.setStep(15);

        PromQLResponse<PromQLResultValues> response = promQLTemplate.queryRange(request);

        Assertions.assertEquals("success", response.getStatus());
    }
}
