package com.example.demoapi.api.controller;

import com.example.demoapi.kafka.producer.UserInfoProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
@Slf4j
public class KafkaController {

    private final UserInfoProducer userInfoProducer;

    @GetMapping("/go")
    public ResponseEntity<String> sendKafkaMessages() throws InterruptedException {
        userInfoProducer.sendUserInfoEvent();
        return ResponseEntity.ok("Messages sent");
    }
}
