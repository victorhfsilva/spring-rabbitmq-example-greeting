package com.example.spring_rabbitmq_example_greeting.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
public class Greeting {
    private String subject;
    private LocalDateTime timestamp;
}
