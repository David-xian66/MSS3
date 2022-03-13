package top.mss3.mssroot.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class MyCookie {
    public static void setCookie(HttpServletResponse response, String key, String value, int expiry){
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(expiry);
        cookie.setSecure(true);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
