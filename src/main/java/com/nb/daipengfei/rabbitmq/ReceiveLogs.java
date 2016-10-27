package com.nb.daipengfei.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Random;

/*********************************
 *                               *
 Created by daipengfei on 16/8/26.
 *                               *
 ********************************/

public class ReceiveLogs {
    private static final String EXCHANGE_NAME = "logs";

    private static final Random r = new Random();

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout",true);
        String queueName = channel.queueDeclare("test-queue",true,false,true,null).getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }
}
