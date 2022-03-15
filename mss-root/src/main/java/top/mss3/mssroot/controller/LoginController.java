package top.mss3.mssroot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import top.mss3.mssroot.model.BaseResult;
import top.mss3.mssroot.model.TokenResult;
import top.mss3.mssroot.model.User;
import top.mss3.mssroot.utils.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(tags = "登录类")
@RestController
@RequestMapping("/api-login")
public class LoginController {
    /**
     * 用户登录
     */
    @ApiOperation(value = "用户登录", notes = "管理用户登录以及Token")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "UserName",
                    value = "用户名",
                    required = true,
                    paramType = "query",
                    dataType = "string",
                    dataTypeClass = String.class
            ),
            @ApiImplicitParam(
                    name = "PassWord",
                    value = "密码",
                    required = true,
                    paramType = "query",
                    dataType = "string",
                    dataTypeClass = String.class
            )
    })
    @GetMapping("/userLogin")
    public BaseResult UserLogin(
            @RequestParam(name = "UserName") String UserName,
            @RequestParam(name = "PassWord") String PassWord,
            HttpServletResponse response){
        User loginUser = new User(UserName, PassWord);
        BaseResult result = MyUser.isUserLogin(loginUser);
        if(result.getCode() == 1){
            MyLog.info("用户：" + UserName + "登录成功");
            MyCookie.setCookie(response, "Token", MyToken.getToken(loginUser), 5 * 24 * 60 * 60);
        }else if(result.getCode() == -1){
            MyLog.error("API：UserLogin 出现错误！" + result.getMsg());
        }
        return result;
    }

    /**
     * 检测Token是否存在
     */
    @ApiOperation(value = "Token校验", notes = "验证Token是否存在")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "Token",
                    value = "Token",
                    required = true,
                    paramType = "header",
                    dataType = "string",
                    dataTypeClass = String.class
            ),
    })
    @GetMapping("/isLive")
    public BaseResult isLive(HttpServletRequest request) {
        TokenResult tokenResult = MyToken.isToken(request);
        if(tokenResult == TokenResult.SUCCESS_TOKEN){
            return new BaseResult<>(1, "Token 正确", "");
        }else {
            return new BaseResult<>(-5, "Token 错误", "");
        }
    }

    /**
     * 退出登录
     */
    @ApiOperation(value = "退出登录", notes = "清除Token并且重定向到登录页面")
    @GetMapping("/loginOut")
    public BaseResult logOut(HttpServletRequest request, HttpServletResponse response){
        Cookie userToken = MyCookie.getCookie(request, "Token");
        if(userToken == null){
            return new BaseResult<>(0, "你并没有登录", "");
        }
        MyCookie.setCookie(response, "Token", null, 0);
        try{
            response.sendRedirect("../");
        }catch (Exception e){
            MyLog.error(e.getMessage());
            return new BaseResult<>(-1, "重定向失败，但已经退出登录，请手动关闭此页面", "");
        }
        return new BaseResult<>(1, "已退出登录", "");
    }
}
