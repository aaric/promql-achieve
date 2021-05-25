package com.example.promql;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * PromQLApiTests
 *
 * @author Aaric, created on 2020-11-23T09:33.
 * @version 0.4.0-SNAPSHOT
 */
@Disabled
@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PromQLApiTests {

    @Value("${promql.base-url}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * http://localhost:9090/api/v1/query?query=up&time=1621936105
     */
    @Test
    public void testQuery() {
        String query = "redis_commands_total{cmd=\"auth\"}";
        String url = baseUrl + "/api/v1/query?query={0}&time={1}";
        String data = restTemplate.getForObject(url, String.class, query, Instant.now().getEpochSecond());

        log.info("{}", data);
        Assertions.assertNotNull(data);
    }

    /**
     * http://localhost:9090/api/v1/query_range
     */
    @Test
    public void testQueryRange() {
        Long current = Instant.now().getEpochSecond();
        String query = "redis_commands_total{cmd=\"auth\"}";
        String url = baseUrl + "/api/v1/query_range";
        Map<String, Object> uriVariables = new HashMap<>(5);
        uriVariables.put("query", query);
        uriVariables.put("start", current - 60 * 60); // now() - 1h
        uriVariables.put("end", current);
        uriVariables.put("step", "15");

        String data = restTemplate.getForObject(url, String.class, uriVariables);

        log.info("{}", data);
        Assertions.assertNotNull(data);
    }
}
