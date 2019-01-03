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
        try {
            System.out.println("Received event " + event.value());
            Thread.sleep(2000);
            System.out.println("Done");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
