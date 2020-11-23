package com.example.pa.promql;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
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
        PromQLRequest request = new PromQLRequest("up{job=\"prometheus\"}", 1606108770369L);
        PromQLResponse response = promQLTemplate.query(request);
    }

    @Test
    public void testQueryRange() {

    }
}
