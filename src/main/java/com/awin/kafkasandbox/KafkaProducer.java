package com.awin.kafkasandbox;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.stereotype.Component;


@Component
public class KafkaProducer {

    @Autowired
    private KafkaOperations<String, String> producer;

    public void produce() {
        int counter = 0;
        while(counter <= 750) {

            for (int i = 0; i < 10; i++) {
                producer.send("TestTopic", DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()));
            }

            try {
                Thread.sleep(1000);
            } catch (Exception ex) {

            }

            counter++;
        }
    }

}
