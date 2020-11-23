package com.example.pa.promql;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private String baseUrl;

    /**
     * Spring Rest Template
     */
    private RestTemplate restTemplate;

    public PromQLTemplate(String baseUrl, RestTemplate restTemplate) {
        this.baseUrl = baseUrl;
        this.restTemplate = restTemplate;
    }

    /**
     * Query Single Metric
     *
     * @param request Request Object
     * @return
     */
    public PromQLResponse<PromQLResultValue> query(PromQLRequest request) {

        Map<String, String> uriVariables = new HashMap<>(3);
        uriVariables.put("query", request.getQuery());
        uriVariables.put("time", "" + request.getTime());
        uriVariables.put("_", "" + Instant.now().toEpochMilli());

        String url = baseUrl + "/api/v1/query?query={query}&time={time}&_={_}";
        ResponseEntity<PromQLResponse<PromQLResultValue>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<PromQLResponse<PromQLResultValue>>() {
                }, uriVariables);

        if (HttpStatus.OK == response.getStatusCode()) {
            return response.getBody();
        }

        return null;
    }

    /**
     * Query Range Metric
     *
     * @param request Request Object
     * @return
     */
    public PromQLResponse<PromQLResultValues> queryRange(PromQLRangeRequest request) {

        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("query", "up");
        uriVariables.put("start", "1606093670.735");
        uriVariables.put("end", "1606097270.735");
        uriVariables.put("step", "14");
        uriVariables.put("_", "" + Instant.now().toEpochMilli());

        return null;
    }
}
