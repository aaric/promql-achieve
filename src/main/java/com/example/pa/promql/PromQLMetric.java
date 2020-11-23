package com.example.pa.promql;

import lombok.Data;

/**
 * PromQL Result Metric
 *
 * @author Aaric, created on 2020-11-23T11:24.
 * @version 0.4.0-SNAPSHOT
 */
@Data
public class PromQLMetric {

    private String __name__;

    private String instance;

    private String job;
}
