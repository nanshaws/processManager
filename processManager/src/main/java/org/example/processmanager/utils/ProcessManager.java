package org.example.processmanager.utils;

import java.io.IOException;
/**
 * @author cyl
 */
public class ProcessManager {
    public static void start(String name) {
        // 命令行指令，启动 name 服务
        String startCommand = "sc start "+name;
        try {
            // 启动 name 服务
            Process startProcess = new ProcessBuilder("cmd", "/c", startCommand).start();
            startProcess.waitFor();
            System.out.println(name+"服务已启动");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void stop(String name){
         // 命令行指令，关闭 name 服务
         String stopCommand = "sc stop "+name;
         // 关闭 name 服务
        Process stopProcess = null;
        try {
            stopProcess = new ProcessBuilder("cmd", "/c", stopCommand).start();
            stopProcess.waitFor();
            System.out.println(name+"服务已关闭");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
