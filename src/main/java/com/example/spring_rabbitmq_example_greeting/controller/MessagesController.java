package com.example.spring_rabbitmq_example_greeting.controller;

import com.example.spring_rabbitmq_example_greeting.service.RabbitMQSender;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MessagesController {

    private RabbitMQSender rabbitMQSender;

    @GetMapping("/pt_br/send")
    public void sendPtBrMessage(){
        rabbitMQSender.sendPtBrGreeting();
    }

    @GetMapping("/en/send")
    public void sendEnMessage(){
        rabbitMQSender.sendEnGreeting();
    }

    @GetMapping("/send")
    public void sendAllMessages(){
        rabbitMQSender.sendAllGreetings();
    }
}
