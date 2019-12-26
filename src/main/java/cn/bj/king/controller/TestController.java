package cn.bj.king.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class TestController {

    @RequestMapping(value = "index")
    public String index(Map<String,Object> map){
        map.put("name","刘海龙");
        map.put("age","25");
        return "index";
    }
}
