package cn.bj.king.controller;

import cn.bj.king.base.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplateController
 */
@RestController
public class RestTemplateController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "rest-test",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseMessage restTemplateTest(){
        ResponseEntity<ResponseMessage> responseMessage=restTemplate.getForEntity("http://localhost:8080/accounts/{id}",ResponseMessage.class,"1");
        ResponseMessage response=responseMessage.getBody();
        return response;
    }

}
