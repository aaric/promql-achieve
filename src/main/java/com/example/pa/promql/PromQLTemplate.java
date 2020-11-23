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
    public PromQLResponse query(PromQLRequest request) {

        Map<String, String> uriVariables = new HashMap<>(3);
        uriVariables.put("query", request.getQuery());
        uriVariables.put("time", "" + request.getTime() / 1000);
        uriVariables.put("_", "" + Instant.now().toEpochMilli());

        // http://gke-master:9090/api/v1/query?query=up&time=1606108770.369&_=1606108693847
        String requestUrl = serverAddress + "/api/v1/query?query={query}&time={time}&_={_}";

        System.out.println(restTemplate.getForObject(requestUrl, String.class, uriVariables));

        PromQLResponse response = restTemplate.getForObject(requestUrl, PromQLResponse.class, uriVariables);

        System.err.println(response.getStatus());

        return null;
    }

    /**
     * Query Range Metric
     *
     * @param request Request Object
     * @return
     */
    public PromQLResultValues queryRange(PromQLRangeRequest request) {
        return null;
    }
}
