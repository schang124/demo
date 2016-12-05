package com.slipp.web;

import com.slipp.model.User;

import javax.servlet.http.HttpSession;

/**
 * Created by schang124 on 2016/12/04.
 */
public class HttpSessionUtils {
    public static final String USER_SESSION_KEY = "sessionedUser";

    public static boolean isLoginUser(HttpSession session) {
        Object sessionedUser = session.getAttribute(USER_SESSION_KEY);
        return (sessionedUser == null) ? false : true;
    }

    public static User getUserFromSession(HttpSession session) {
        if(!isLoginUser(session))
            return null;

        return (User)session.getAttribute(USER_SESSION_KEY);
    }


}
