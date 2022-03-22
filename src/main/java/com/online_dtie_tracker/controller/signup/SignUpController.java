package com.online_dtie_tracker.controller.signup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    @GetMapping("/home")
    public String getSignUpPage(){
        return "signuppage/signuppage";
    }
}
