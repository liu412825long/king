package cn.bj.king.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Create by 刘海龙 2019年12月30日
 * Copyright (c) 2019 神工众志科技有限公司
 * {在这里补充类的功能说明}
 *
 * @author 刘海龙 hailong.liu@0071515.com
 */
@Controller
public class PageController {

    @RequestMapping(value = "/")
    public String pageLogin(){
        return "login";
    }
    @RequestMapping(value = "/404")
    public String page404(){
        return "404";
    }
    @RequestMapping(value = "index")
    public String index(Map<String,Object> map){
        map.put("name","刘海龙");
        map.put("age","25");
        return "index";
    }
}
