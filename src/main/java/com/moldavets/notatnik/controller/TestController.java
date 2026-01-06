package com.moldavets.notatnik.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/tests")
    public String runTests() {
        return "index.html";
    }

    @GetMapping("/main.js")
    public String getTestsConfig() {
        return "main.js";
    }

}
