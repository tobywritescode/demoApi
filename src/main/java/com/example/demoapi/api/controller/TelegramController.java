package com.example.demoapi.api.controller;

import com.example.demoapi.service.TelegramService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/telegram")
public class TelegramController {

    @Autowired
    TelegramService tg;

    @PostMapping("/sendmessage")
    public ResponseEntity<String> sendTelegramMessage(@RequestBody String message){
        return ResponseEntity.ok(tg.postTelegramMessage(message));
    }

}
