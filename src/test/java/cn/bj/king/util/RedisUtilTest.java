package cn.bj.king.util;

import cn.bj.king.KingApplicationTests;
import cn.bj.king.dto.AccountDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class RedisUtilTest extends KingApplicationTests {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void set() throws Exception {
        redisUtil.set("accountName","liuhailong");
    }

    @Test
    public void set1() throws Exception {
        redisUtil.set("smsCode","278493",10);
    }

    @Test
    public void get() throws Exception {
        System.out.println(redisUtil.get("accountName"));
    }

    @Test
    public void del() throws Exception {
        redisUtil.del("accountName");
    }

    @Test
    public void exist() throws Exception {
        set();
        System.out.println(redisUtil.exist("accountName"));
    }

    @Test
    public void incr() throws Exception {
        redisUtil.incr("num",3);
    }

    @Test
    public void decr() throws Exception {
        redisUtil.decr("num",2);
    }

    @Test
    public void hmset() throws Exception {
        Map<String,Object> accountDTOMap=new HashMap<>();
        accountDTOMap.put("username","liu");
        accountDTOMap.put("username1","liuhai");
        accountDTOMap.put("username2","liuhailong");
        redisUtil.hmset("accountInfo",accountDTOMap);
    }

    @Test
    public void hmset1() throws Exception {
        Map<String,Object> accountDTOMap=new HashMap<>();
        accountDTOMap.put("username","liu");
        accountDTOMap.put("username1","liuhai");
        redisUtil.hmset("accountInfo",accountDTOMap,10);
    }

    @Test
    public void hset() throws Exception {
    }

    @Test
    public void hset1() throws Exception {
    }

    @Test
    public void hget() throws Exception {
    }

    @Test
    public void hmget() throws Exception {
    }

    @Test
    public void hdel() throws Exception {
    }

    @Test
    public void hHasKey() throws Exception {
    }

    @Test
    public void hincr() throws Exception {
    }

    @Test
    public void hdecr() throws Exception {
    }

    @Test
    public void sGet() throws Exception {
    }

    @Test
    public void sHasKey() throws Exception {
    }

    @Test
    public void sSet() throws Exception {
    }

    @Test
    public void sSetAndTime() throws Exception {
    }

    @Test
    public void sGetSetSize() throws Exception {
    }

    @Test
    public void setRemove() throws Exception {
    }

    @Test
    public void lGet() throws Exception {
    }

    @Test
    public void lGetListSize() throws Exception {
    }

    @Test
    public void lGetIndex() throws Exception {
    }

    @Test
    public void lSet() throws Exception {
    }

    @Test
    public void lSet1() throws Exception {
    }

    @Test
    public void lSet2() throws Exception {
    }

    @Test
    public void lSet3() throws Exception {
    }

    @Test
    public void lUpdateIndex() throws Exception {
    }

    @Test
    public void lRemove() throws Exception {
    }

}