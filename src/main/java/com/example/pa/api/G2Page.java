package com.example.pa.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;

/**
 * G2Page
 *
 * @author Aaric, created on 2020-12-02T17:30.
 * @version 0.8.0-SNAPSHOT
 */
@Api(tags = "G2Page")
public interface G2Page {

    @ApiOperation("Chart")
    String chart(Model model, String type);
}
