package com.nb.daipengfei.kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

/*********************************
 *                               *
 Created by daipengfei on 16/10/13.
 *                               *
 ********************************/

public class KafkaProducer {
    private final Producer<String, String> producer;
    public final static String TOPIC = "message_bus_contacts";

    private KafkaProducer(){
        Properties props = new Properties();
        //此处配置的是kafka的端口
        props.put("metadata.broker.list", "192.168.2.200:9092");

        //配置value的序列化类
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        //配置key的序列化类
        props.put("key.serializer.class", "kafka.serializer.StringEncoder");

        //request.required.acks
        //0, which means that the producer never waits for an acknowledgement from the broker (the same behavior as 0.7). This option provides the lowest latency but the weakest durability guarantees (some data will be lost when a server fails).
        //1, which means that the producer gets an acknowledgement after the leader replica has received the data. This option provides better durability as the client waits until the server acknowledges the request as successful (only messages that were written to the now-dead leader but not yet replicated will be lost).
        //-1, which means that the producer gets an acknowledgement after all in-sync replicas have received the data. This option provides the best durability, we guarantee that no messages will be lost as long as at least one in sync replica remains.
        props.put("request.required.acks","-1");

        producer = new Producer<String, String>(new ProducerConfig(props));
    }

    void produce() {
        int messageNo = 1;
        final int COUNT = 2;

        while (messageNo < COUNT) {
            String key = String.valueOf(messageNo);
            String data = "{\"content\":{\"from_service\":\"contacts\",\"event_type\":\"create\",\"user_id\":\"150000132\",\"category\":\"contacts\",\"resource_location\":\"150000132\",\"custom_info\":null,\"data\":\"{\\\"contacts\\\":[{\\\"name\\\":\\\"飞珏\\\",\\\"phones\\\":[\\\"18969121111\\\"]},{\\\"name\\\":\\\"李庚\\\",\\\"phones\\\":[\\\"13625816748\\\"]},{\\\"name\\\":\\\"-1430952744_18\\\",\\\"phones\\\":[\\\"15503774872\\\"]},{\\\"name\\\":\\\"-1430952744_19\\\",\\\"phones\\\":[\\\"14682870763\\\"]},{\\\"name\\\":\\\"-1430952744_16\\\",\\\"phones\\\":[\\\"14076216776\\\"]},{\\\"name\\\":\\\"-1430952744_17\\\",\\\"phones\\\":[\\\"12678402036\\\"]},{\\\"name\\\":\\\"-1430952744_14\\\",\\\"phones\\\":[\\\"11712412867\\\"]},{\\\"name\\\":\\\"-1430952744_15\\\",\\\"phones\\\":[\\\"13868543338\\\"]},{\\\"name\\\":\\\"-1430952744_12\\\",\\\"phones\\\":[\\\"11227182551\\\"]},{\\\"name\\\":\\\"-1430952744_0\\\",\\\"phones\\\":[\\\"16832117680\\\"]},{\\\"name\\\":\\\"-1430952744_13\\\",\\\"phones\\\":[\\\"11570464266\\\"]},{\\\"name\\\":\\\"-1430952744_10\\\",\\\"phones\\\":[\\\"14380670647\\\"]},{\\\"name\\\":\\\"-1430952744_11\\\",\\\"phones\\\":[\\\"11602362043\\\"]},{\\\"name\\\":\\\"-1430952744_3\\\",\\\"phones\\\":[\\\"11537565178\\\"]},{\\\"name\\\":\\\"-1430952744_4\\\",\\\"phones\\\":[\\\"16343456078\\\"]},{\\\"name\\\":\\\"-1430952744_1\\\",\\\"phones\\\":[\\\"12036051465\\\"]},{\\\"name\\\":\\\"-1430952744_2\\\",\\\"phones\\\":[\\\"12125537380\\\"]},{\\\"name\\\":\\\"圣兰1\\\",\\\"phones\\\":[\\\"137-85671720\\\",\\\"13485671720\\\"]},{\\\"name\\\":\\\"-1430952744_7\\\",\\\"phones\\\":[\\\"14444465563\\\"]},{\\\"name\\\":\\\"-1430952744_8\\\",\\\"phones\\\":[\\\"13337853678\\\"]},{\\\"name\\\":\\\"-1430952744_5\\\",\\\"phones\\\":[\\\"12388757018\\\"]},{\\\"name\\\":\\\"-1430952744_6\\\",\\\"phones\\\":[\\\"12007360824\\\"]},{\\\"name\\\":\\\"-1430952744_9\\\",\\\"phones\\\":[\\\"16752765647\\\"]}]}\"},\"uuid\":\"6ce684fb-2f88-40ad-92a1-e9709448de82\",\"time\":\"1476351605532\",\"big_app_id\":\"0\",\"service\":\"contacts\",\"tag\":\"tc_contacts\"}";
            producer.send(new KeyedMessage<String, String>(TOPIC, key ,data));
            System.out.println(data);
            messageNo ++;
        }
    }

    public static void main( String[] args )
    {
        new KafkaProducer().produce();
    }
}
