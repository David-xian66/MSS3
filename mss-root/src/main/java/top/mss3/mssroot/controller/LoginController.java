package top.mss3.mssroot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import top.mss3.mssroot.model.BaseResult;
import top.mss3.mssroot.model.TokenResult;
import top.mss3.mssroot.model.User;
import top.mss3.mssroot.utils.*;

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
        try{
            if(!MyData.IsJsonData("Users")){
                JSONObject users = new JSONObject();
                JSONObject master = new JSONObject();
                JSONArray Users = new JSONArray();
                master.put("UserName", "Master");
                master.put("PassWord", "e807f1fcf82d132f9bb018ca6738a19f");
                Users.add(master);
                users.put("Users", Users);
                MyData.SetJsonData(users, "MSSUsers");
            }
            for (User user : MyUser.GetUsers()){
                if(user.equals(loginUser)){
                    MyLog.info("用户：" + loginUser.getUserName() + "登录成功");
                    MyCookie.setCookie(response, "Token", MyToken.getToken(loginUser), 5 * 24 * 60 * 60);
                    return new BaseResult(1, "登录成功", "");
                }
            }
            return new BaseResult(0, "用户名或密码错误", "");
        }catch (Exception e) {
            MyLog.error("API：UserLogin 出现错误！" + e.getMessage());
            return new BaseResult(-1, e.getMessage(), "");
        }
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
            return new BaseResult(1, "Token 正确", "");
        }else {
            return new BaseResult(1, "Token 正确", "");
        }
    }
}
