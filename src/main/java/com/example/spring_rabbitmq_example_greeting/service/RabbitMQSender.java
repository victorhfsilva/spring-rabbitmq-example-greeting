package com.example.spring_rabbitmq_example_greeting.service;

import com.example.spring_rabbitmq_example_greeting.config.RabbitMQConfig;
import com.example.spring_rabbitmq_example_greeting.model.Greeting;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RabbitMQSender {

    private RabbitTemplate rabbitTemplate;

    public void sendPtBrGreeting() {
        Greeting greeting = Greeting.builder()
                .subject("Victor")
                .timestamp(LocalDateTime.now())
                .build();
        rabbitTemplate.convertAndSend(RabbitMQConfig.GREETING_TOPIC_EXCHANGE, "greeting.pt_br", greeting);
    }

    public void sendEnGreeting() {
        Greeting greeting = Greeting.builder()
                .subject("Victor")
                .timestamp(LocalDateTime.now())
                .build();
        rabbitTemplate.convertAndSend(RabbitMQConfig.GREETING_TOPIC_EXCHANGE, "greeting.en", greeting);
    }

    public void sendAllGreetings() {
        Greeting greeting = Greeting.builder()
                .subject("Victor")
                .timestamp(LocalDateTime.now())
                .build();
        rabbitTemplate.convertAndSend(RabbitMQConfig.GREETING_FANOUT_EXCHANGE, "greetings", greeting);
    }
}
