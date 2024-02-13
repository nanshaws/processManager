package org.example.processmanager.utils;

import java.io.IOException;
/**
 * @author cyl
 */
public class OpenExporer {
    public static void main(String[] args) throws IOException, InterruptedException {
        String command = "explorer";
        Process process = Runtime.getRuntime().exec(command);
    }
}
