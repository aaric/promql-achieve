package com.example.pa.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 测试Java监控API接口
 *
 * @author Aaric, created on 2020-11-17T10:25.
 * @version 0.3.0-SNAPSHOT
 */
@Api(tags = "测试Java监控API")
public interface PrometheusApi {

    @ApiOperation("GET请求计数")
    String get();

    @ApiOperation("POST请求计数")
    String post();
}
