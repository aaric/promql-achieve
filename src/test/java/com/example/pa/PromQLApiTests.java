package com.example.pa;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

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

    @Autowired
    private RestTemplate restTemplate;

    /**
     * http://localhost:9090/api/v1/query?query=up&time=1606097235.13&_=1606096696180
     */
    @Test
    public void testQuery() {
        String url = "http://localhost:9090/api/v1/query?query={1}&time={2}&_={3}";
        String data = restTemplate.getForObject(url, String.class, "up", "1606097235.13", "1606096696180");

        log.info("{}", data);
        Assertions.assertNotNull(data);
    }

    /**
     * http://localhost:9090/api/v1/query_range?query=up&start=1606093670.735&end=1606097270.735&step=14&_=1606096696181
     */
    @Test
    public void testQueryRange() {
        Map<String, Object> uriVariables = new HashMap<>(5);
        uriVariables.put("query", "up");
        uriVariables.put("start", "1606093670.735");
        uriVariables.put("end", "1606097270.735");
        uriVariables.put("step", "15");
        uriVariables.put("_", "1606096696181");

        String url = "http://localhost:9090/api/v1/query_range?query={query}&start={start}&end={end}&step={step}&_={_}";
        String data = restTemplate.getForObject(url, String.class, uriVariables);

        log.info("{}", data);
        Assertions.assertNotNull(data);
    }
}
