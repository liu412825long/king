package cn.bj.king.service;

import cn.bj.king.KingApplicationTests;
import cn.bj.king.dto.AccountDTO;
import cn.bj.king.util.MD5Coder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

public class AccountServiceImplTest extends KingApplicationTests {

    @Autowired
    private AccountService accountService;

    @Test
    public void createAccount() throws Exception {
        List<AccountDTO> list= createDTO();

        accountService.batchCreateAccount(list);
//        ExecutorService executor= Executors.newFixedThreadPool(5);
//
//        for(int i=0;i<10;i++){
//
//            executor.execute(new Runnable() {
//                @Override
//                public void run() {
//
//
//
//
//                }
//            });
//
//        }
    }

   private List<AccountDTO> createDTO(){
        List<AccountDTO> list=new ArrayList<>();
       AccountDTO accountDTO=null;
        for(int i=0;i<1000000;i++){
            accountDTO=new AccountDTO();
            String username="username_"+i;
            accountDTO.setUsername(username);
            accountDTO.setPassword(MD5Coder.encode(username));
            list.add(accountDTO);
        }
        return list;
   }

}