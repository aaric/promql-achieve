package com.example.pa.api.impl;

import com.example.pa.api.VegaLitePage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * VegaLiteController
 *
 * @author Aaric, created on 2020-12-01T15:59.
 * @version 0.7.0-SNAPSHOT
 */
@Controller
@RequestMapping("/page/vega-lite")
public class VegaLiteController implements VegaLitePage {

    @Override
    @GetMapping("/chart/{mark}")
    public String chart(Model model, @PathVariable String mark) {

        model.addAttribute("title", "Vega-Lite " + StringUtils.capitalize(mark) + " Sample");

        return "chart/vega/" + mark;
    }
}
