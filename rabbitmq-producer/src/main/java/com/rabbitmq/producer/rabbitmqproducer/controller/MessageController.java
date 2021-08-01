package com.rabbitmq.producer.rabbitmqproducer.controller;

import com.rabbitmq.producer.rabbitmqproducer.model.SimpleMessage;
import com.rabbitmq.producer.rabbitmqproducer.producer.MessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageProducer messageProducer;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void postMessage(@RequestBody SimpleMessage simpleMessage) {
        messageProducer.publishMessage(simpleMessage);
    }
}
