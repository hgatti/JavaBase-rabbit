package com.base.JavaBase.controller;

import com.base.JavaBase.controller.dto.MessageRequest;
import com.base.JavaBase.sender.RabbitSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private RabbitSender rabbitSender;

    @PostMapping(value = "/rabbit/send", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendMessage(@RequestBody MessageRequest request) {

        if (request.getMessage().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            rabbitSender.send(request.getMessage());
            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
