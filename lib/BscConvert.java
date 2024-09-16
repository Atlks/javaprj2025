package lib;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class BscConvert {

    /**
     * Converts a String to an InputStream.
     *
     * @param str the String to be converted
     * @return an InputStream representing the String
     */
    public static InputStream stringToInputStream(String str) {
        if (str == null) {
            return null; // or throw IllegalArgumentException
        }
        // Convert String to byte array using UTF-8 encoding
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        // Create a ByteArrayInputStream from the byte array
        return new ByteArrayInputStream(bytes);
    }
}
