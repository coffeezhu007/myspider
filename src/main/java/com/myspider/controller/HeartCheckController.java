package com.myspider.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HeartCheckController {

    @RequestMapping("/heart_check")
    public String heartCheck() {
        return "heart_check";
    }
}