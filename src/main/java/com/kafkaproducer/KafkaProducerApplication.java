package com.kafkaproducer;

import com.kafkaproducer.producer.EmployeeJsonProducer;
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

	@Autowired
	private EmployeeJsonProducer employeeJsonProducer;

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {



	}
}
