package com.example.pa.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;

/**
 * VegaLitePage
 *
 * @author Aaric, created on 2020-12-01T15:58.
 * @version 0.7.0-SNAPSHOT
 */
@Api(tags = "VegaLitePage")
public interface VegaLitePage {

    @ApiOperation("Chart")
    String chart(Model model, String mark);
}
