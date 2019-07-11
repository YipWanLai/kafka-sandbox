package com.awin.kafkasandbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;


@Component
public class BeanCaller implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private KafkaProducer producer;

    public void onApplicationEvent(ContextRefreshedEvent e) {
        System.out.println("START");
        producer.produce();
    }
}
