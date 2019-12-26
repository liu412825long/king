package cn.bj.king.amqp.receiver;

import cn.bj.king.amqp.constant.QueueNameConstant;
import cn.bj.king.dto.AccountDTO;
import cn.bj.king.util.JSON;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQReceiver {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 注意这里的AccountDTO 必须要实现Serializable 接口，不然会报错
     * @param accountDTO
     */
    public void send(AccountDTO accountDTO) {
        rabbitTemplate.convertAndSend(QueueNameConstant.SEND_MESSAGE_QUEUE, accountDTO);
    }


    @RabbitListener(queues = QueueNameConstant.SEND_MESSAGE_QUEUE)
    @RabbitHandler
    public void receive(AccountDTO accountDTO) {
        System.out.println(JSON.stringify(accountDTO));
    }



}
