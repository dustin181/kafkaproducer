package com.kafkaproducer.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KeyProducer {

    private KafkaTemplate<String, String> kafkaTemplate;

    public KeyProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String key, String data) {
        kafkaTemplate.send("t_hello", key, data);
    }
}