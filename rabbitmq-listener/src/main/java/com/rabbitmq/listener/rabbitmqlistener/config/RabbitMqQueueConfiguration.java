package com.rabbitmq.listener.rabbitmqlistener.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// This class will create new Queues
@Configuration
public class RabbitMqQueueConfiguration {

    @Bean
    Queue exampleQueue() {
        return new Queue("ExampleQueue", false);
    }

    @Bean
    Queue example2ndQueue() {
        return QueueBuilder
                .durable("Example2ndQueue")
                .autoDelete()
                .exclusive()
                .build();
    }
}
