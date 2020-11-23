package com.example.pa.promql;

import lombok.Data;

import java.util.List;

/**
 * PromQL Result Value
 *
 * @author Aaric, created on 2020-11-23T11:24.
 * @version 0.4.0-SNAPSHOT
 */
@Data
public class PromQLResultValue {

    /**
     * Result Type: "matrix" | "vector" | "scalar" | "string"
     */
    private String resultType;

    /**
     * Result Body
     */
    private List<Value> result;

    @Data
    public static class Value {

        private PromQLMetric metric;

        private String[] value;
    }
}
