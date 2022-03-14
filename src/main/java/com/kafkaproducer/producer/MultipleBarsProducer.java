package com.kafkaproducer.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@PropertySource("classpath:secrets.properties")
@Service
public class MultipleBarsProducer {

    public static final String APCA_API_BASE_URL = "https://data.alpaca.markets";
    public static final String ENDPOINT = "/v2/stocks/SPY/bars?timeframe=5Min&start=2022-03-07T00:00:00.990804253Z&end=2022-03-11T00:00:00.990804253Z";

    private KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper = new ObjectMapper();
    private RestTemplate restTemplate = new RestTemplate();

    public MultipleBarsProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 30000)
    public void send() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content_Type", "application/json");
        headers.set("APCA-API-KEY-ID", System.getenv("APCA-API-KEY-ID"));
        headers.set("APCA-API-SECRET-KEY", System.getenv("APCA-API-SECRET-KEY"));

        String endpoint = APCA_API_BASE_URL + ENDPOINT;

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                endpoint, HttpMethod.GET, requestEntity, String.class);

        String bars = response.getBody();

        if(response.getStatusCode().value() == 200){
            log.info(bars);
            kafkaTemplate.send("t_stockquote", bars);
        }
    }
}

