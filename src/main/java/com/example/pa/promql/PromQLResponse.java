package com.example.pa.promql;

import lombok.Data;

/**
 * promql-achieve
 *
 * @author Aaric, created on 2020-11-23T13:25.
 * @version 0.1.0-SNAPSHOT
 */
@Data
public class PromQLResponse<T> {

    /**
     * Status: success | error
     */
    private String status;

    /**
     * Real Data
     */
    private T data;

    /**
     * Error Type: bad_data...
     */
    private String errorType;

    /**
     * Error Message
     */
    private String error;
}
