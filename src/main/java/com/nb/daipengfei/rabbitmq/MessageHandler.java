package com.nb.daipengfei.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/*********************************
 *                               *
 Created by daipengfei on 16/9/8.
 *                               *
 ********************************/
@Component
public class MessageHandler {

    @RabbitListener(queues = "helloQ")
    public void receive(Message message){
        System.out.println("hello: "+message);
    }

}
