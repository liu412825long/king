package cn.bj.king.controller;

import cn.bj.king.KingApplicationTests;
import cn.bj.king.dto.AccountDTO;
import cn.bj.king.util.JSON;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AccountControllerTest extends KingApplicationTests {


    @Autowired
    private AccountController accountController;

    MockMvc mockMvc;

    @Before
    public void before(){
        mockMvc= MockMvcBuilders.standaloneSetup(accountController).build();

    }

    @Test
    public void findById() throws Exception {
        RequestBuilder requestBuilder=MockMvcRequestBuilders.get("/accounts/1");
        String result=mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void createAccount() throws Exception {
        AccountDTO accountDTO=new AccountDTO();
        accountDTO.setUsername("刘海龙");
        accountDTO.setPassword("123456");
        accountDTO.setPhone("12345678910");
        String json= JSON.stringify(accountDTO);
        RequestBuilder requestBuilder=MockMvcRequestBuilders.post("/accounts").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(json);
        String result=mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void updateAccount() throws Exception {
        AccountDTO accountDTO=new AccountDTO();
        accountDTO.setUsername("ARongking");
        String json=JSON.stringify(accountDTO);
        RequestBuilder requestBuilder=MockMvcRequestBuilders.put("/accounts/1001").contentType(MediaType.APPLICATION_JSON).content(json);
        String result=mockMvc.perform(requestBuilder).andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void deleteAccount() throws Exception {
        RequestBuilder requestBuilder=MockMvcRequestBuilders.delete("/accounts/1001");
        String result=mockMvc.perform(requestBuilder).andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }


    @Test
    public void queryByPage() throws Exception {
        RequestBuilder requestBuilder=MockMvcRequestBuilders.get("/accounts").param("pageNum","1").param("pageSize","10");
        String result=mockMvc.perform(requestBuilder).andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }


}