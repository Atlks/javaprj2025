package libx.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
    public static String encrypt(String s) {

        try {
            // 创建一个 MD5 消息摘要实例
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 更新消息摘要
            md.update(s.getBytes());
            // 获取摘要的字节数组
            byte[] digest = md.digest();

            // 将字节数组转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0'); // 补零
                hexString.append(hex);
            }
            return hexString.toString(); // 返回 MD5 加密后的字符串
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e); // 处理异常
        }
    }

}


