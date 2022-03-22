package com.online_dtie_tracker.controller.signup;

import com.online_dtie_tracker.Dto.UserDto;
import com.online_dtie_tracker.conversion.DtoModelConvert;
import com.online_dtie_tracker.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;

@Controller
@RequestMapping("/signup")
public class SignUpController {
    //add userService for database
    private final UserServiceImpl userService;

    public SignUpController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String getSignUpPage(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "signuppage/signuppage";
    }

    @PostMapping("/add")
    public String getSignUp(@Valid @ModelAttribute("userDto") UserDto userDto,
                            BindingResult bindingResult, Model model) throws IOException, ParseException {
        if (!bindingResult.hasErrors()) {
            //first check user password is match or not
            if (userDto.getPassword().equals(userDto.getReTypePassword())){
                //save into database
              UserDto userDto1  = userService.save(userDto);

              if (userDto1 !=null){
                  model.addAttribute("message","User Added successfully.");
              }else {
                  model.addAttribute("message", "Failed to add user");
              }

            }else {
                //if user password isnot match
                model.addAttribute("passwordMsg","password must match");
            }
        }

        //if there is any error
        model.addAttribute("userDto", userDto);
        return "signuppage/signuppage";
    }
}
