package com.example.pa.promql;

import lombok.Data;

/**
 * PromQL Response
 *
 * @author Aaric, created on 2020-11-23T10:40.
 * @version 0.4.0-SNAPSHOT
 */
@Data
public class PromQLResponse {

    /**
     * Status: success | error
     */
    private String status;

    /**
     * Real Data
     */
    private String data;

    /**
     * Error Type: bad_data...
     */
    private String errorType;

    /**
     * Error Message
     */
    private String error;
}
