package com.online_dtie_tracker.authorizeduser;

import com.online_dtie_tracker.model.User;

public class AuthorizedUser {
    /*
    here we store the detail of authorized  user for this application
     */
    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        AuthorizedUser.user = user;
    }
}
