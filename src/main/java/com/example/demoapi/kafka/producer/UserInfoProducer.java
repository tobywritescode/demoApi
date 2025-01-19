package com.example.demoapi.kafka.producer;

import com.example.demoapi.model.entity.people.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserInfoProducer {

    private final KafkaTemplate<String, Object> template;

    @Value("${kafka.topic-name}")
    private String topicName;

    public void sendUserInfoEvent() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(100);
        IntStream.rangeClosed(1, 100).forEach(i -> {
            template.send(topicName, String.valueOf(i), new UserInfo(String.valueOf(i), "test", i));
            log.info("message sent");
        });
        latch.await(5, TimeUnit.SECONDS);
        log.info("messages sent");
    }

}

