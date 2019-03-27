package cn.bj.king.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Eric
 */
public class MD5Coder {
    /**
     *
     */
    private static final String ALGORITHM = "MD5";
    /**
     *
     */
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String encode(String str) {
        if (str == null) {
            return null;
        }
        return encode(str.getBytes());
    }

    public static String encode(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }

        try {
            MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            md.update(bytes);
            return getFormattedText(md.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取格式化的文本字符串
     *
     * @param bytes
     * @return
     */
    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuffer sb = new StringBuffer();
        for (int j = 0; j < len; j++) {
            sb.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            sb.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return sb.toString();
    }
}
