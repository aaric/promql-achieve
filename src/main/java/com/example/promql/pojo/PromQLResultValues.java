package com.example.promql.pojo;

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

    /**
     * Result Type: "matrix" | "vector" | "scalar" | "string"
     */
    private String resultType;

    /**
     * Result Body
     */
    private List<Values> result;

    @Data
    public static class Values {

        private PromQLMetric metric;

        private List<String[]> values;
    }
}
