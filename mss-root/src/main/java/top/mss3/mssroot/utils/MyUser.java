package top.mss3.mssroot.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import top.mss3.mssroot.model.BaseResult;
import top.mss3.mssroot.model.User;

import java.util.ArrayList;
import java.util.List;

public class MyUser {
    /**
     * 获取所有用户
     */
    public static List<User> GetUsers(){
        List<User> result = new ArrayList<>();
        for (Object dataUserObj : JSONArray.parseArray(MyData.GetJsonData("MSSUsers").get("Users").toString()))
            result.add(((JSONObject) dataUserObj).toJavaObject(User.class));
        return result;
    }

    /**
     * 使用用户名寻找用户
     * @param UserName 用户名
     */
    public static User findUserByUserName(String UserName){
        for (User user : GetUsers()) {
            if(user.getUserName().equals(UserName)){
                return user;
            }
        }
        return null;
    }

    /**
     * 用户登录验证
     * @param loginUser 登录用户
     */
    public static BaseResult isUserLogin(User loginUser) {
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
                    return new BaseResult<>(1, "登录成功", "");
                }
            }
            return new BaseResult<>(0, "用户名或密码错误", "");
        }catch (Exception e) {
            return new BaseResult<>(-1, e.getMessage(), "");
        }
    }
}
