package com.example.pa.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;

/**
 * IndexPage
 *
 * @author Aaric, created on 2020-11-25T16:52.
 * @version 0.5.0-SNAPSHOT
 */
@Api(tags = "IndexPage")
public interface IndexPage {

    @ApiOperation("index")
    String home(Model model);
}
