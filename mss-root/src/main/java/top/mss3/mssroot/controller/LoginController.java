package top.mss3.mssroot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.mss3.mssroot.utils.MyData;
import top.mss3.mssroot.utils.MyLog;

import java.util.Map;

@RestController
@RequestMapping("/api-login")
public class LoginController {
    /**
     * 用户登录
     * @param params UserName:用户名 PassWord:密码
     * @return 0:用户名或密码错误 1:登录成功
     */
    @GetMapping("/userLogin")
    public String UserLogin(@RequestParam Map<String, Object> params){
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
            for (Object dataUserObj : JSONArray.parseArray(MyData.GetJsonData("MSSUsers").get("Users").toString())){
                JSONObject dataUser = (JSONObject) dataUserObj;
                if(dataUser.get("UserName").equals(params.get("UserName")) && dataUser.get("PassWord").equals(params.get("PassWord"))){
                    MyLog.info("用户：" + params.get("UserName") + "登录成功");
                    return "1";
                }
            }
            return "0";
        }catch (Exception e) {
            MyLog.error("API：UserLogin 出现错误！" + e.getMessage());
            return "-1|" + e.getMessage();
        }
    }
}
