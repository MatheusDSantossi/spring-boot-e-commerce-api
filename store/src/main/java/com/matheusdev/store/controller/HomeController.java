package com.matheusdev.store.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Value("${spring.application.name}")
    private String appPageSize;

    @RequestMapping("/")
    public String index() {
        String viewName = getViewName();
        return viewName;
        // return "index.html";
    }

    private String getViewName() {
        System.out.println("appName: " + appPageSize);
        return "index.html";
//        return "index2";
    }
}
