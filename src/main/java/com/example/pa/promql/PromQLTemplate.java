package com.example.pa.promql;

import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * PromQL Api Utils
 *
 * @author Aaric, created on 2020-11-23T11:35.
 * @version 0.4.0-SNAPSHOT
 */
public class PromQLTemplate {

    /**
     * Prometheus Server Address
     */
    private String serverAddress;

    /**
     * Spring Rest Template
     */
    private RestTemplate restTemplate;

    public PromQLTemplate(String serverAddress, RestTemplate restTemplate) {
        this.serverAddress = serverAddress;
        this.restTemplate = restTemplate;
    }

    /**
     * Query Single Metric
     *
     * @param request Request Object
     * @return
     */
    public PromQLData<PromQLResultValue> query(PromQLRequest request) {

        Map<String, String> uriVariables = new HashMap<>(3);
        uriVariables.put("query", request.getQuery());
        uriVariables.put("time", "" + request.getTime() / 1000);
        uriVariables.put("_", "" + Instant.now().toEpochMilli());

        String requestUrl = serverAddress + "/api/v1/query?query={query}&time={time}&_={_}";
        String data = restTemplate.getForObject(requestUrl, String.class, uriVariables);

        System.err.println(data);

        return null;
    }

    /**
     * Query Range Metric
     *
     * @param request Request Object
     * @return
     */
    public PromQLData<PromQLResultValues> queryRange(PromQLRangeRequest request) {
        return null;
    }
}
