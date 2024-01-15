package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("simple")
public class HTMLController {

    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/ui")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "myHtml";
    }
}
