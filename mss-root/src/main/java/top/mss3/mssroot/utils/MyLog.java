package top.mss3.mssroot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.mss3.mssroot.MssRootApplication;

public class MyLog {
    private static final Logger log = LoggerFactory.getLogger(MssRootApplication.class);

    public static void info(String msg){
        log.info("[MSS 3核心] " + msg);
    }

    public static void error(String msg){
        log.error("[MSS 3核心] " + msg);
    }
}
