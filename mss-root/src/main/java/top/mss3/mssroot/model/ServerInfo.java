package top.mss3.mssroot.model;

public class ServerInfo {
    String serverId;
    int status;
    String type;
    String name;

    public ServerInfo(String serverId, int status, String type, String name){
        this.serverId = serverId;
        this.status = status;
        this.type = type;
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getServerId() {
        return serverId;
    }

    public String getType() {
        return type;
    }
}
