package com.example.promql.page.impl;

import com.example.promql.page.HomePage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HomeController
 *
 * @author Aaric, created on 2021-05-24T17:24.
 * @version 0.8.0-SNAPSHOT
 */
@Controller
@RequestMapping("/page/home")
public class HomeController implements HomePage {

    @Override
    @GetMapping("/index")
    public String index(Model model) {

        model.addAttribute("title", "Prometheus Chart Sample");

        return "index";
    }
}
