package top.mss3.mssroot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.mss3.mssroot.model.BaseResult;
import top.mss3.mssroot.model.ServerInfo;
import top.mss3.mssroot.model.TokenResult;
import top.mss3.mssroot.utils.MyServer;
import top.mss3.mssroot.utils.MyToken;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api-server")
public class ServerController {

    /**
     * 获取所有服务器信息
     */
    @GetMapping("/getServers")
    public ServersResult getServers(HttpServletRequest request){
        TokenResult tokenResult = MyToken.isToken(request);
        if(tokenResult == TokenResult.SUCCESS_TOKEN){
            return new ServersResult<>(1, "获取成功", new ServersResult.ServersData(MyServer.GetServerInfoList().size(), MyServer.GetServerInfoList()));
        }else {
            return new ServersResult<>(-5, "Token 错误", "");
        }
    }

}
class ServersResult<T>{
    int code;
    String msg;
    T data;

    ServersResult(int code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    static class ServersData {
        int count;
        List<ServerInfo> servers;

        ServersData(int count, List<ServerInfo> servers){
            this.count = count;
            this.servers = servers;
        }

        public int getCount() {
            return count;
        }

        public List<ServerInfo> getServers() {
            return servers;
        }
    }
}
