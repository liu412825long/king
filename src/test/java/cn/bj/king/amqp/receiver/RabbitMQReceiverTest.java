package cn.bj.king.amqp.receiver;

import cn.bj.king.KingApplicationTests;
import cn.bj.king.dto.AccountDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class RabbitMQReceiverTest extends KingApplicationTests {

    @Autowired
    private RabbitMQReceiver receiver;

    @Test
    public void send() throws Exception {
        AccountDTO accountDTO=new AccountDTO();
        accountDTO.setUsername("ARongking");
        accountDTO.setPhone("1234567890");
        receiver.send(accountDTO);
    }

}