package com.example.pa.promql;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * PromQL Result Value
 *
 * @author Aaric, created on 2020-11-23T11:24.
 * @version 0.4.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class PromQLResultValue extends PromQLResult {

    private String[] value;
}
