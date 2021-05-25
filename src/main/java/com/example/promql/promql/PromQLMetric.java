package com.example.promql.promql;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * PromQL Result Metric
 *
 * @author Aaric, created on 2020-11-23T11:24.
 * @version 0.4.0-SNAPSHOT
 */
@Data
public class PromQLMetric {

    @JsonProperty("__name__")
    private String name;

    private String instance;

    private String job;
}
