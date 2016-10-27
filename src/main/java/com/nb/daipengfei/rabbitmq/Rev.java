package com.nb.daipengfei.rabbitmq;

import com.rabbitmq.client.*;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;

import java.io.IOException;

/*********************************
 *                               *
 Created by daipengfei on 16/8/26.
 *                               *
 ********************************/

public class Rev {
    private final static String QUEUE_NAME = "helloQ";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();
        channel.basicQos(3);
        AMQP.Queue.DeclareOk declareOk = channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(declareOk.getQueue().hashCode());
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
                System.out.println(envelope.getDeliveryTag());
//                channel.basicNack(envelope.getDeliveryTag(),false,true);
                try{
                    Thread.sleep(500);
                }catch (Exception e){
                    //ignore
                }
            }
        };
        channel.basicConsume(QUEUE_NAME, false, consumer);
    }

}
