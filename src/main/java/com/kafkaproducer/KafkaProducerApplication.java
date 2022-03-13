package com.kafkaproducer;

import com.kafkaproducer.producer.FixedRateProducer;
import com.kafkaproducer.producer.HelloKafkaProducer;
import com.kafkaproducer.producer.KeyProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class KafkaProducerApplication implements CommandLineRunner {

	@Autowired
	private HelloKafkaProducer helloKafkaProducer;

	@Autowired
	private FixedRateProducer fixedRateProducer;

	@Autowired
	private KeyProducer keyProducer;

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		helloKafkaProducer.sendHello("TestName" + Math.random());
//		fixedRateProducer.sendMessage();
		for(int i = 0;i < 30;i++){
			String key = "key-" + i%4;
			String data = "data " + i + " with key " + key;
			keyProducer.send(key, data);
			log.info("message was: " + key + data);

		}

	}
}
