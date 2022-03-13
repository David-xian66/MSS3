package top.mss3.mssroot.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import top.mss3.mssroot.model.TokenResult;
import top.mss3.mssroot.model.User;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;

public class MyToken {

    /**
     * Token验证
     * @return Token验证结果
     */
    public static TokenResult isToken(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("Token");
        if (token == null) {
            return TokenResult.NO_TOKEN;
        }
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            return TokenResult.TOKEN_ERROR;
        }
        User user = MyUser.findUserByUserName(userId);
        if (user == null) {
            return TokenResult.USER_NOTFOUND;
        }
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            return TokenResult.PASSWORD_ERROR;
        }
        return TokenResult.SUCCESS_TOKEN;
    }

    /**
     * 获取Token所属用户
     * @return 用户
     */
    public static User getTokenUser(HttpServletRequest request) {
        String token = request.getHeader("Token");
        return MyUser.findUserByUserName(JWT.decode(token).getAudience().get(0));
    }

    /**
     * 获取Token
     * @param user 用户
     * @return Token
     */
    public static String getToken(User user) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60 * 60 * 1000 * 24 * 5;//五天有效时间
        Date end = new Date(currentTime);
        String token;
        token = JWT.create()
                .withAudience(user.getUserName())
                .withIssuedAt(start)
                .withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}