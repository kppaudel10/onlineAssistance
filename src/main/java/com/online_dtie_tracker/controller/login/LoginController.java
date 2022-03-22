package com.online_dtie_tracker.controller.login;

import com.online_dtie_tracker.Dto.LoginDto;
import com.online_dtie_tracker.authorizeduser.AuthorizedUser;
import com.online_dtie_tracker.model.User;
import com.online_dtie_tracker.service.impl.LoginServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {
    //get login service
    private final LoginServiceImpl loginService;

    public LoginController(LoginServiceImpl loginService) {
        this.loginService = loginService;
    }
    
    //store Authorized user
    private static User authorizedUser;
    
    @GetMapping("/home")
    public String getLoginPage(Model model){
        model.addAttribute("loginDto",new LoginDto());
        return "loginpage/loginpage";
    }

    //check valid user ot not
    @PostMapping("/home")
    public String getCheckValidUserOrNot(@Valid @ModelAttribute("loginDto")LoginDto loginDto,
                                         BindingResult bindingResult,Model model){
        if (!bindingResult.hasErrors()){
            //then check valid user or not
         User user = loginService.isValidUser(loginDto.getUserName(),loginDto.getPassword());
            if (user.getId() !=null){
                //go for that valid user home page
                //set as authorized user
                AuthorizedUser.setUser(user);

                model.addAttribute("authorizedUser",user);
                return "userhomepage/userhomepage";
            }else {
                //back to login page
                model.addAttribute("InvalidMessage","invalid username or password");
                model.addAttribute("loginDto",loginDto);
                return "loginpage/loginpage";
            }
        }
            //go to that page again
            model.addAttribute("loginDto",loginDto);
            return "loginpage/loginpage";

    }
}
