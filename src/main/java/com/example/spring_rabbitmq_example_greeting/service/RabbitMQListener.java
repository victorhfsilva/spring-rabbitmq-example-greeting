package com.example.spring_rabbitmq_example_greeting.service;

import com.example.spring_rabbitmq_example_greeting.config.RabbitMQConfig;
import com.example.spring_rabbitmq_example_greeting.model.Greeting;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RabbitMQListener {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQListener.class);

    @RabbitListener(queues = RabbitMQConfig.GREETING_PT_BR_QUEUE)
    public void recievePtBrGreeting(@Payload Greeting greeting) {
       String message = "Ola " + greeting.getSubject() + ". Sao " + greeting.getTimestamp() + ".";
       logger.info(message);
   }

    @RabbitListener(queues = RabbitMQConfig.GREETING_EN_QUEUE)
    public void recieveEnGreeting(@Payload Greeting greeting) {
        String message = "Hello " + greeting.getSubject() + ". It is " + greeting.getTimestamp() + ".";
        logger.info(message);
    }

    @RabbitListener(queues = RabbitMQConfig.GREETING_NOTIFICATION_QUEUE)
    public void recieveGreetingNotification(@Payload Greeting greeting){
       if (greeting != null){
           logger.info("A greeting was received.");
       }
    }
}
