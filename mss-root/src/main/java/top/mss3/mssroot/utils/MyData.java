package top.mss3.mssroot.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.*;

public class MyData {
    public static boolean SetJsonData(JSONObject Data, String FileName){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "/data/" + FileName + ".json"));
            out.write(Data.toJSONString());
            out.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static JSONObject GetJsonData(String FileName){
        StringBuilder over = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/data/" + FileName + ".json"));
            String line;
            while((line = in.readLine()) != null){
                over.append(line);
            }
            in.close();
        } catch (IOException e) {
            return new JSONObject();
        }
        return JSONObject.parseObject(String.valueOf(over));
    }

    public static boolean IsJsonData(String FileName){
        return new File(System.getProperty("user.dir") + "/data/" + FileName + ".json").exists();
    }
}
