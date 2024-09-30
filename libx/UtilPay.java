package libx;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UtilPay {


    public static void loginfoLn(Object contentObj) {


        try {
            String content = JSONObject.toJSONString(contentObj);
            if (isString(contentObj))
                content = (String) contentObj;



            println(content);
            String filePath = "/logs/xydpay.log";


            appendToFileLn(filePath, content);
            filePath = "/logs/xydpay.log";
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

    }

    private static void println(String content) {
        System.out.println(content);
    }

    public static boolean isString(Object obj) {
        return obj instanceof String;
    }

    public static void appendToFileLn(String filePath, String content) {
        File file = new File(filePath);
        // 确保父目录存在
        file.getParentFile().mkdirs();

        BufferedWriter writer = null;

        try {
            // 以追加模式打开文件
            writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(content);
            writer.newLine(); // 添加换行符
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.err.println("Error closing writer: " + e.getMessage());
                }
            }
        }
    }
}
