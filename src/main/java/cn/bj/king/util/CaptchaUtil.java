package cn.bj.king.util;

import com.github.cage.Cage;
import com.github.cage.GCage;

import java.util.UUID;

/**
 * Created by Arongking
 */
public class CaptchaUtil {

    private static int CAPTCHA_LENGTH = 4;

    private static Cage cage = new GCage();

    public static byte[] getImageChallengeForID(String token) throws Exception {
        String drawToken = hash(token);
        return cage.draw(drawToken);
    }

    public static boolean validateImageChallengeForID(String token, String captcha) {
        if (hash(token).equals(captcha)) {
            return true;
        }
        else{
            return false;
        }
    }

    private static String hash(String token) {
        int sum = 0;
        String result = "";
        for (int i = 0; i < token.length(); i++) {
            sum += token.charAt(i);
        }
        for (int i = 0; i < CAPTCHA_LENGTH; i++) {
            result += token.charAt(sum % token.length());
            sum = sum * CAPTCHA_LENGTH;
        }
        return result;
    }
}
