package com.online_dtie_tracker.controller.logout;

import com.online_dtie_tracker.authorizeduser.AuthorizedUser;
import com.online_dtie_tracker.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class LogOutController {

    @GetMapping("")
    public String getLogOut(){
        //first make Authorized user as null then go to login page
        AuthorizedUser.setUser(new User());
        return "redirect:/login/home";
    }
}
