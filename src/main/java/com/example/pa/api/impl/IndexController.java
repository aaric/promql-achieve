package com.example.pa.api.impl;

import com.example.pa.api.IndexPage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * IndexController
 *
 * @author Aaric, created on 2020-11-25T16:55.
 * @version 0.5.0-SNAPSHOT
 */
@Controller
@RequestMapping("/page/index")
public class IndexController implements IndexPage {

    @Override
    @GetMapping("/home")
    public String home(Model model) {

        model.addAttribute("title", "Vega-Lite Bar Sample");

        return "home";
    }
}
