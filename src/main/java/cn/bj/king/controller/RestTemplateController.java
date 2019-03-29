package cn.bj.king.controller;

import cn.bj.king.base.ResponseMessage;
import cn.bj.king.config.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * RestTemplateController
 */
@RestController
@RequestMapping(value = "restTemplate")
public class RestTemplateController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApplicationConfig applicationConfig;

    @GetMapping(value = "rest-test",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseMessage restTemplateTest(){
        ResponseEntity<ResponseMessage> responseMessage=restTemplate.getForEntity("http://localhost:8080/accounts/{id}",ResponseMessage.class,"1");
        ResponseMessage response=responseMessage.getBody();
        return response;
    }
    @GetMapping(value = "queryById",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseMessage queryById(){
//        Map<String,Object> param=new HashMap<>();
//        param.put("id",1);
//        ResponseEntity<ResponseMessage> responseMessage=restTemplate.getForEntity("http://localhost:8080/accounts/queryById",ResponseMessage.class,param);
//        ResponseMessage response=responseMessage.getBody();
        Map<String,Object> param=new HashMap<>();
        param.put("id",1);
        ResponseMessage responseMessage=restTemplate.getForObject("http://localhost:8080/accounts/queryById?id={id}",ResponseMessage.class,param);
        return responseMessage;
    }

    /**
     * 获取读取的配置文件
     * @return
     */
    @GetMapping(value = "env",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getEnv(){
        return applicationConfig.getEnvironment();
    }

}
