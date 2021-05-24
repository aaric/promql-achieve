package com.example.pa.api.impl;

import com.example.pa.api.G2Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * G2Controller
 *
 * @author Aaric, created on 2020-12-02T17:31.
 * @version 0.8.0-SNAPSHOT
 */
@Controller
@RequestMapping("/page/g2")
public class G2Controller implements G2Page {

    @Override
    @GetMapping("/chart/{type}")
    public String chart(Model model, @PathVariable String type) {

        model.addAttribute("title", "AntV G2 " + StringUtils.capitalize(type) + " Sample");

        return "chart/g2/" + type;
    }
}
