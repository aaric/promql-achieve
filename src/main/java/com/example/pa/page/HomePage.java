package com.example.pa.page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;

/**
 * HomePage
 *
 * @author Aaric, created on 2021-05-24T17:23.
 * @version 0.8.0-SNAPSHOT
 */
@Api(tags = "HomePage")
public interface HomePage {

    @ApiOperation("index")
    String index(Model model);
}
