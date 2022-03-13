package top.mss3.mssroot.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import top.mss3.mssroot.model.User;

import java.util.ArrayList;
import java.util.List;

public class MyUser {
    public static List<User> GetUsers(){
        List<User> result = new ArrayList<>();
        for (Object dataUserObj : JSONArray.parseArray(MyData.GetJsonData("MSSUsers").get("Users").toString()))
            result.add(((JSONObject) dataUserObj).toJavaObject(User.class));
        return result;
    }

    public static User findUserByUserName(String UserName){
        for (User user : GetUsers()) {
            if(user.getUserName().equals(UserName)){
                return user;
            }
        }
        return null;
    }
}
