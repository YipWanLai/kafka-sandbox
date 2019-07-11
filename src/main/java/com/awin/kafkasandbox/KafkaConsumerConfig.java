package com.awin.kafkasandbox;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;


@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String servers;

    private Map<String, Object> consumerProps(String bootstrapServers) {
        return new HashMap<String, Object>() {
            {
                put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
                put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
                put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 5000);
                put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 30000);
                put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 25000);
                put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
                put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
                put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
                put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, 100);
                put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, 10000);

                put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 3000);
                put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1);
            }
        };
    }

    private ConsumerFactory consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerProps(servers));
    }

    @Bean("kafkaContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(1);
        factory.getContainerProperties().setAckMode(AbstractMessageListenerContainer.AckMode.RECORD);
        factory.getContainerProperties().setAckOnError(false);
        return factory;
    }

}
