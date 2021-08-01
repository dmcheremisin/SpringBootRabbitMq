package com.rabbitmq.listener.rabbitmqlistener.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RabbitMqMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println(">>> Received message: " + message.getMessageProperties());
        System.out.println(">>> Message body: " + new String(message.getBody()));
    }
}
