package com.example.pa.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * MetricsApi
 *
 * @author Aaric, created on 2020-12-01T13:50.
 * @version 0.5.0-SNAPSHOT
 */
@Api(tags = "MetricsApi")
public interface MetricsApi {

    @ApiOperation("Counter")
    String counter();

    @ApiOperation("Gauge")
    String gauge();

    @ApiOperation("Summary")
    String summary();
}
