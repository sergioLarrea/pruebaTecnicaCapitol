package com.capitol.pruebatecnicacapitol.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final String kafkaTopic = "topico";

    public void send(String message) {

        kafkaTemplate.send(kafkaTopic, message);
    }
}
