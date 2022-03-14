package top.mss3.mssroot.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class MyCookie {
    /**
     * 设置Cookie
     * @param response Http返回
     * @param key 名称
     * @param value 值
     * @param expiry 到期时间
     */
    public static void setCookie(HttpServletResponse response, String key, String value, int expiry){
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(expiry);
        cookie.setSecure(true);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 获取Cookie
     * @param request Http请求
     * @param name 名称
     * @return Cookie
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap.getOrDefault(name, null);
    }
}
