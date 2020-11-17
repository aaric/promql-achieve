package com.example.pa.api.impl;

import com.example.pa.api.PrometheusApi;
import com.example.pa.runner.PrometheusRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试Java监控API接口实现
 *
 * @author Aaric, created on 2020-11-17T10:26.
 * @version 0.3.0-SNAPSHOT
 */
@RestController
@RequestMapping("/api/prometheus")
public class PrometheusController implements PrometheusApi {

    @Override
    @GetMapping("/get")
    public String get() {
        // add
        PrometheusRunner.HTTP_GET_COUNTER.increment();

        // return
        return "method: get - " + PrometheusRunner.HTTP_GET_COUNTER.count();
    }

    @Override
    @PostMapping("/post")
    public String post() {
        // add
        PrometheusRunner.HTTP_POST_COUNTER.increment();

        // return
        return "method: post - " + PrometheusRunner.HTTP_POST_COUNTER.count();
    }
}
