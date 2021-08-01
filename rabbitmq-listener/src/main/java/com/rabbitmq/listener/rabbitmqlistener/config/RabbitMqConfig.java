package com.rabbitmq.listener.rabbitmqlistener.config;

import com.rabbitmq.listener.rabbitmqlistener.listener.RabbitMqMessageListener;
import lombok.val;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// This class will establish connection and configure listener
@Configuration
public class RabbitMqConfig {

    public static final String MY_QUEUE = "MyQueue";
    public static final String MY_TOPIC_EXCHANGE = "MyTopicExchange";
    public static final String ROUTING_KEY = "topic";

    @Bean
    public Queue myQueue() {
        return new Queue(MY_QUEUE, true);
    }

    @Bean
    Exchange myExchange() {
        return ExchangeBuilder
                .topicExchange(MY_TOPIC_EXCHANGE)
                .durable(true)
                .build();
    }

    @Bean
    Binding building() {
        //return new Binding(MY_QUEUE, Binding.DestinationType.QUEUE, MY_TOPIC_EXCHANGE, "topic", null);
        return BindingBuilder
                .bind(myQueue())
                .to(myExchange())
                .with(ROUTING_KEY)
                .noargs();
    }

    @Bean
    ConnectionFactory connectionFactory() {
        val factory = new CachingConnectionFactory("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");

        return factory;
    }

    @Bean
    MessageListenerContainer messageListenerContainer() {
        val container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueues(myQueue());
        container.setMessageListener(new RabbitMqMessageListener());

        return container;
    }
}
