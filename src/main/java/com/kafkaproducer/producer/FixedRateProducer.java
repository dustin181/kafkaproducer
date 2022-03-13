package com.kafkaproducer.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FixedRateProducer {

    private KafkaTemplate<String, String> kafkaTemplate;

    private int i = 0;

    public FixedRateProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 1000)
    public void sendMessage() {
        i++;
        log.info("i is " + i);
        kafkaTemplate.send("t_fixedrate", "Fixed rate " + i);
    }


}
