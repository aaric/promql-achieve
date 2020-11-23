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

    private String resultType;

    private List<Value> result;

    @Data
    public static class Value {

        private PromQLResultMetric metric;

        private String[] value;
    }
}
