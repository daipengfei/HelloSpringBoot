package com.nb.daipengfei.kafka;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/*********************************
 *                               *
 Created by daipengfei on 16/10/13.
 *                               *
 ********************************/

public class KafkaConsumer {
    private final ConsumerConnector consumer;

    static final Executor e = Executors.newFixedThreadPool(5);

    private KafkaConsumer() {
        Properties props = new Properties();
        //zookeeper 配置
        props.put("zookeeper.connect", "127.0.0.1:2181");
        //group 代表一个消费组
        props.put("group.id", "dpf-group");

        //zk连接超时
        props.put("zookeeper.session.timeout.ms", "4000");
        props.put("zookeeper.sync.time.ms", "20000");
        props.put("auto.commit.interval.ms", "10000");
        props.put("auto.offset.reset", "smallest");
        //序列化类
        props.put("serializer.class", "kafka.serializer.StringEncoder");

        ConsumerConfig config = new ConsumerConfig(props);

        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config);
    }

    void consume() {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(KafkaProducer.TOPIC, 5);

        StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
        StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());

        Map<String, List<KafkaStream<String, String>>> consumerMap =
                consumer.createMessageStreams(topicCountMap,keyDecoder,valueDecoder);
        List<KafkaStream<String, String>> streams = consumerMap.get(KafkaProducer.TOPIC);
        for(final KafkaStream<String,String> stream:streams){
            e.execute(new Runnable() {
                @Override
                public void run() {
                    for(MessageAndMetadata<String,String> k:stream){
                        System.out.println("received! "+ k.message());
                    }
                }
            });

        }

    }

    public static void main(String[] args) {
        new KafkaConsumer().consume();
    }
}
