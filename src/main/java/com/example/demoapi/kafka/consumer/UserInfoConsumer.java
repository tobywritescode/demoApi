package com.example.demoapi.kafka.consumer;

import com.example.demoapi.model.entity.people.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class UserInfoConsumer {

    @KafkaListener(topics = "userInfo-topic", clientIdPrefix = "json", containerFactory = "kafkaListenerContainerFactory")
    public void consumeUserInfoEvent(ConsumerRecord<String, UserInfo> consumerRecord, @Payload UserInfo userInfo){
        log.info("consuming user info {}", userInfo);

    }
}
