package org.example.processmanager.utils;

import org.example.processmanager.pojo.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author cyl
 */
public class ServiceManager {

    public static List<Service> serviceList=new ArrayList<>();

    public static String name;
    public static String statu;
    public static void getAny() {
        try {
            // 执行 sc query 命令来获取服务列表
            Process process = new ProcessBuilder("cmd", "/c", "sc", "query").start();
            // 读取命令输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("GBK")));
            String line;
            while ((line = reader.readLine()) != null) {
                Service service = new Service();
                // 在这里你可以解析每一行来获取服务的信息
                if (line.trim().startsWith("DISPLAY_NAME:")) {
                    name = line.trim().substring("DISPLAY_NAME:".length()).trim();
                }
                Pattern statePattern = Pattern.compile("STATE\\s+:\\s+(\\d+)\\s+(\\w+)");
                Matcher stateMatcher = statePattern.matcher(line);
                if (stateMatcher.find()) {
                    statu= stateMatcher.group(2);
                }
                if (name!=null&&statu!=null){
                    service.setName(name);
                    service.setStatus(statu);
                    serviceList.add(service);
                    name=null;
                    statu=null;
                }
            }
            // 等待命令执行完成
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void clearList() {
        serviceList.clear();
    }
}
