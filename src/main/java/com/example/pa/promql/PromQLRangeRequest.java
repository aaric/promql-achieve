package com.example.pa.promql;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PromQL Range Request
 *
 * @author Aaric, created on 2020-11-23T10:42.
 * @version 0.4.0-SNAPSHOT
 */
@Data
@NoArgsConstructor
public class PromQLRangeRequest {

    /**
     * PromQL Expression
     */
    protected String query;

    /**
     * Query Start Seconds
     */
    private long start;

    /**
     * Query End Seconds
     */
    private long end;

    /**
     * Step Seconds
     */
    private int step;

    public PromQLRangeRequest(String query) {
        this.query = query;
    }
}
