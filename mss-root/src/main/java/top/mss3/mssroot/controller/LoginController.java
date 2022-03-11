package top.mss3.mssroot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api-login")
public class LoginController {

    @ResponseBody
    @RequestMapping("/userLogin")
    public String UserLogin(){
        return "I Got It!";
    }
}
