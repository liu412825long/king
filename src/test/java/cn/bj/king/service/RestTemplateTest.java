package cn.bj.king.service;

import cn.bj.king.KingApplicationTests;
import cn.bj.king.base.ResponseMessage;
import cn.bj.king.dto.AccountDTO;
import cn.bj.king.util.JSON;
import cn.bj.king.vo.AccountVO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestTemplateTest extends KingApplicationTests {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void postTest() {
        String url = "http://localhost:8080/accounts";
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUsername("zhangsan");
        accountDTO.setPhone("12345678901");
        accountDTO.setPassword("1234567890");
//        String result=restTemplate.postForObject(url,accountDTO,String.class);
//        System.out.println("............."+JSON.stringify(result));
//        ResponseMessage<Integer> responseMessage=restTemplate.postForObject(url,accountDTO,ResponseMessage.class);
//        System.out.println("............."+JSON.stringify(responseMessage));

    }

    @Test
    public void postTest1() {
        String url = "http://localhost:8080/accounts/queryList";
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(30);
        accountDTO.setUsername("zhangsan");
        accountDTO.setPhone("12345678901");
        accountDTO.setPassword("1234567890");
        ResponseMessage<AccountVO> responseMessage = restTemplate.postForObject(url, accountDTO, ResponseMessage.class);
        System.out.println("............." + JSON.stringify(responseMessage));
//        String  responseMessage=restTemplate.postForObject(url,accountDTO,String.class);
//        System.out.println("............."+JSON.stringify(responseMessage));
//        HttpEntity<AccountDTO> request = new HttpEntity<>(accountDTO);
//        String responseEntity=restTemplate.postForObject(url,accountDTO,String.class);
//        System.out.println("............."+JSON.stringify(responseEntity));

    }


    @Test
    public void postTest2() {
        String url = "http://localhost:8080/accounts/queryListMap";
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(30);
        accountDTO.setUsername("zhangsan");
        accountDTO.setPhone("12345678901");
        accountDTO.setPassword("1234567890");
        AccountDTO accountDTO1 = new AccountDTO();
        accountDTO.setId(31);
        accountDTO.setUsername("liuhailong");
        String param=getUrlParamsByMap(objectToMap(accountDTO1));
        ResponseMessage<AccountVO> responseMessage = restTemplate.postForObject(url+"?"+param, accountDTO, ResponseMessage.class);
        System.out.println("............." + JSON.stringify(responseMessage));
//        String  responseMessage=restTemplate.postForObject(url,accountDTO,String.class);
//        System.out.println("............."+JSON.stringify(responseMessage));
//        HttpEntity<AccountDTO> request = new HttpEntity<>(accountDTO);
//        String responseEntity=restTemplate.postForObject(url,accountDTO,String.class);
//        System.out.println("............."+JSON.stringify(responseEntity));

    }

    @Test
    public  void test() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(30);
        accountDTO.setUsername("zhangsan");
        accountDTO.setPhone("12345678901");
        accountDTO.setPassword("1234567890");
        try {
//            System.out.println(JSON.stringify(objectToMap(accountDTO)));
            System.out.println(getUrlParamsByMap(objectToMap(accountDTO)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> objectToMap(Object obj){
        if (obj == null)
            return null;

        Map<String, Object> map = new HashMap<String, Object>();
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter != null ? getter.invoke(obj) : null;
            map.put(key, value);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 将map转换成url
     *
     * @param map
     * @return
     */
    public static String getUrlParamsByMap(Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = s.substring(0,s.lastIndexOf("&"));
        }
        return s;
    }

}
