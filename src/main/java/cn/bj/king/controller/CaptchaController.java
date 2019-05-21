package cn.bj.king.controller;

import cn.bj.king.base.ResponseMessage;
import cn.bj.king.util.CaptchaUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController(value = "captcha")
public class CaptchaController {

    /**
     * 获取图片验证码
     *
     * @return the captcha token and image
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage<?> getCaptcha(HttpServletResponse response) {
        try {
            String captchaToken = CaptchaUtil.createToken();
            byte[] image = CaptchaUtil.getImageChallengeForID(captchaToken);
            response.addHeader("Captcha-Token", captchaToken);
            Map result = new HashMap();
            result.put("prefix", "data:image/jpg;base64,");
            result.put("image", image);
            return ResponseMessage.build().data(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.build();
        }
    }
    /**
     * 验证图片验证码是否有效
     *
     * @param captchaToken
     * @param captcha
     * @return
     */
    @RequestMapping(value = "/captcha", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseMessage<Boolean> verifyCaptcha(@RequestHeader("Captcha-Token") String captchaToken,
                                             @RequestHeader("captcha") String captcha) {
        try {
            boolean result = CaptchaUtil.validateImageChallengeForID(captchaToken, captcha);
            return ResponseMessage.build().data(result);
        } catch (Exception e) {
            return ResponseMessage.build();
        }
    }
}
