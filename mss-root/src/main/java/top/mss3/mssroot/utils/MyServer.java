package top.mss3.mssroot.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import top.mss3.mssroot.model.ServerInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MyServer {

    /**
     * 获取所有服务器信息
     */
    public static List<ServerInfo> GetServerInfoList(){
        if(!MyData.IsJsonData("ServerInfos")){
            JSONObject servers = new JSONObject();
            JSONObject server = new JSONObject();
            JSONArray Servers = new JSONArray();
            server.put("serverId", UUID.randomUUID().toString());
            server.put("type", "paper");
            server.put("name", "一个服务器");
            Servers.add(server);
            servers.put("ServerInfos", Servers);
            MyData.SetJsonData(servers, "ServerInfos");
        }
        List<ServerInfo> result = new ArrayList<>();
        for (Object dataUserObj : JSONArray.parseArray(MyData.GetJsonData("ServerInfos").get("ServerInfos").toString())){
            JSONObject obj = (JSONObject) dataUserObj;
            result.add(new ServerInfo(
                    obj.getString("serverId"),
                    GetServerStatus(obj.getString("serverId")),
                    obj.getString("type"),
                    obj.getString("name")
            ));
        }
        return result;
    }

    /**
     * 获取服务器的状态
     * @param ServerId 服务器ID
     */
    public static int GetServerStatus(String ServerId){
        return 1;
    }
}
