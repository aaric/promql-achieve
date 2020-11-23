package com.example.pa.promql;

import lombok.Data;

/**
 * PromQL Data
 *
 * @author Aaric, created on 2020-11-23T11:28.
 * @version 0.1.0-SNAPSHOT
 */
@Data
public class PromQLData<T extends PromQLResult> {

    private String resultType;

    private T result;
}
