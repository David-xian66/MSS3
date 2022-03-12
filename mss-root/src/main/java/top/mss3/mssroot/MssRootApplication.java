package top.mss3.mssroot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.mss3.mssroot.utils.MyLog;

import java.io.File;

@SpringBootApplication
public class MssRootApplication {

    public static void main(String[] args) {
        MyLog.info("正在初始化MSS3");
        initFolder();
        SpringApplication.run(MssRootApplication.class, args);
        MyLog.info("初始化MSS3完成！已开放端口8080！");
    }

    private static void initFolder() {
        String[] folders = new String[]{"data"};
        for (String folder : folders){
            File data = new File(System.getProperty("user.dir") + "/" + folder);
            if(!data.exists()) data.mkdir();
        }
    }

}
