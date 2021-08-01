package com.rabbitmq.producer.rabbitmqproducer.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.producer.rabbitmqproducer.model.SimpleMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class MessageProducer {

    public static final String MY_TOPIC_EXCHANGE = "MyTopicExchange";
    public static final String ROUTING_KEY = "topic";

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper;

    @SneakyThrows
    public void publishMessage(SimpleMessage simpleMessage) {
        String stringMessage = mapper.writeValueAsString(simpleMessage);
        rabbitTemplate.convertAndSend(MY_TOPIC_EXCHANGE, ROUTING_KEY, stringMessage);
    }

    private void publishObject() {
        SimpleMessage simpleMessage = new SimpleMessage();
        simpleMessage.setName("some name");
        simpleMessage.setMessage("some message");
        rabbitTemplate.convertAndSend(MY_TOPIC_EXCHANGE, ROUTING_KEY, simpleMessage);
    }

    private void publishTextMessage() {
        rabbitTemplate.convertAndSend(MY_TOPIC_EXCHANGE, ROUTING_KEY, "Hello from code");
    }

//    @PostConstruct
//    public void publishToQueue() {
//        publishTextMessage();
//        publishObject();
//    }
}
