package com.awin.kafkasandbox;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class KafkaConsumer {

    @KafkaListener(
            id = "invoicing-settings",
            topics = "TestTopic",
            groupId = "kafka-sandbox",
            containerFactory = "kafkaContainerFactory"
    )
    public void listen(ConsumerRecord<String, String> event) {
        System.out.println("Received event " + event.value());
        try {
            Thread.sleep(1000);
        } catch (Exception ex) {

        }
    }

}
