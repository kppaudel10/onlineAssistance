package com.online_dtie_tracker.controller.user.dashboard;

import com.online_dtie_tracker.authorizeduser.AuthorizedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @GetMapping("")
    public String getDashboard(Model model){
        model.addAttribute("address", AuthorizedUser.getUser().getAddress());
        return "user/userhomepage";
    }


}
