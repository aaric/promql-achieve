package com.example.promql.promql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PromQL Request
 *
 * @author Aaric, created on 2020-11-23T10:39.
 * @version 0.4.0-SNAPSHOT
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromQLRequest {

    /**
     * PromQL Expression
     */
    private String query;

    /**
     * Query Seconds
     */
    private long time;
}
