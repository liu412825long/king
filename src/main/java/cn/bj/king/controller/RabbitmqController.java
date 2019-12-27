package cn.bj.king.controller;

import cn.bj.king.amqp.receiver.RabbitMQReceiver;
import cn.bj.king.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by 刘海龙 2019年12月26日
 * Copyright (c) 2019 神工众志科技有限公司
 * {在这里补充类的功能说明}
 *
 * @author 刘海龙 hailong.liu@0071515.com
 */
@RestController
@RequestMapping(value = "rabbitmq")
public class RabbitmqController {

    @Autowired
    private RabbitMQReceiver receiver;

    @RequestMapping(value = "send")
    public void send() throws Exception {
        AccountDTO accountDTO=new AccountDTO();
        accountDTO.setUsername("ARongking");
        accountDTO.setPhone("1234567890");
        receiver.send(accountDTO);
    }
}
