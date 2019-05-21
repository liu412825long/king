package cn.bj.king.util;

import cn.bj.king.base.ResponseMessage;
import org.json.JSONObject;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;

import java.util.Map;

import static org.junit.Assert.*;

public class HttpUtilTest {
    @Test
    public void get() throws Exception {
        String url="http://localhost:8080/captcha";
        String response=HttpUtil.get(url);

        ResponseMessage<Map> responseMessage=JSON.parse(response, ResponseMessage.class);

        System.out.println(responseMessage.getData().get("prefix"));
    }

}