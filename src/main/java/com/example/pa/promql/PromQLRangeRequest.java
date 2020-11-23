package com.example.pa.promql;

import lombok.Data;

/**
 * PromQL Range Request
 *
 * @author Aaric, created on 2020-11-23T10:42.
 * @version 0.4.0-SNAPSHOT
 */
@Data
public class PromQLRangeRequest {

    /**
     * PromQL Expression
     */
    protected String query;

    /**
     * Query Start Timestamp
     */
    private long start;

    /**
     * Query End Timestamp
     */
    private long end;

    /**
     * Step
     */
    private int step;
}
