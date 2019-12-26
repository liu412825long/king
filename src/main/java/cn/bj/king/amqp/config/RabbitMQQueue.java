package cn.bj.king.amqp.config;

import cn.bj.king.amqp.constant.QueueNameConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQQueue {

    @Bean(name = QueueNameConstant.SEND_MESSAGE_QUEUE)
    public Queue sendMessageQueue(){
        return new Queue(QueueNameConstant.SEND_MESSAGE_QUEUE);
    }
}
