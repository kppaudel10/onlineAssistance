package com.online_dtie_tracker.controller.income;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/income")
public class IncomeController {

    @GetMapping("/home")
    public String getIncomeHomePage(){
        return "income/incomehomepage";
    }
}
