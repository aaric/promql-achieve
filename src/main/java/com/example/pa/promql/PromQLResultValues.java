package com.example.pa.promql;

import lombok.Data;

import java.util.List;

/**
 * PromQL Result Values
 *
 * @author Aaric, created on 2020-11-23T11:24.
 * @version 0.4.0-SNAPSHOT
 */
@Data
public class PromQLResultValues {

    private String resultType;

    private List<Values> result;

    @Data
    public static class Values {

        private PromQLResultMetric metric;

        private String[] value;
    }
}
