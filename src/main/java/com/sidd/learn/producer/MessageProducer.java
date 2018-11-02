package com.sidd.learn.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class MessageProducer {

    private final static String TOPIC = "wordcount-input";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";

    private Producer<Long, String> producer = null;

    public MessageProducer() {
        this.producer = createProducer();
    }

    private static Producer<Long, String> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return new KafkaProducer(props);
    }

    public void sendMessage(String data)throws Exception {
        Long index = System.currentTimeMillis();
        try {
            ProducerRecord<Long, String> record = new ProducerRecord(TOPIC, index, data);
            RecordMetadata metadata = producer.send(record).get();
            long elapsedTime = System.currentTimeMillis() - index;
            System.out.printf("sent record(key=%s value=%s) " +
                            "meta(partition=%d, offset=%d) time=%d\n",
                    record.key(), record.value(), metadata.partition(),
                    metadata.offset(), elapsedTime);
        }catch(Exception ex) {
            ex.printStackTrace();
            producer.flush();
            producer.close();
        }
    }
}