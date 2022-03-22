package com.online_dtie_tracker.service.impl;

import com.online_dtie_tracker.model.User;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl {
    //get userServiceImpl for find user
    private final UserServiceImpl userService;

    public LoginServiceImpl(UserServiceImpl userService) {
        this.userService = userService;
    }

    //check user is valid or not
    private User getValidUser(String userName){
        return userService.getUserByUserName(userName);
    }

    public User isValidUser(String userName,String password){
        //first get user
      User user = getValidUser(userName);

      //check user is null or not
        if (user !=null){
            //if userName password is match
            if (user.getPassword().equals(password)){
                return user;
            }else {
                return new User();
            }
        }
        else {
            return new User();
        }

    }

}
