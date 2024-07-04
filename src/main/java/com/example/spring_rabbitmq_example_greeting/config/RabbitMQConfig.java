package com.example.spring_rabbitmq_example_greeting.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    public static final String GREETING_PT_BR_QUEUE = "greeting.pt_br";
    public static final String GREETING_EN_QUEUE = "greeting.en";
    public static final String GREETING_NOTIFICATION_QUEUE = "greeting.notification";
    public static final String GREETING_TOPIC_EXCHANGE = "greeting.topic.exchange";
    public static final String GREETING_FANOUT_EXCHANGE = "greeting.fanout.exchange";

    @Bean
    public RabbitAdmin createRabbitAdmin(ConnectionFactory connection) {
        return new RabbitAdmin(connection);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initializeAdmin(RabbitAdmin rabbitAdmin){
        return event -> rabbitAdmin.initialize();
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         Jackson2JsonMessageConverter messageConverter){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public Queue greetingPtBrQueue(){
        return QueueBuilder.nonDurable(GREETING_PT_BR_QUEUE)
                .build();
    }

    @Bean
    public Queue greetingEnQueue(){
        return QueueBuilder.nonDurable(GREETING_EN_QUEUE)
                .build();
    }

    @Bean
    public Queue greetingNotificationQueue(){
        return QueueBuilder.nonDurable(GREETING_NOTIFICATION_QUEUE)
                .build();
    }

    @Bean
    public TopicExchange greetingTopicExchange() {
        return new TopicExchange(GREETING_TOPIC_EXCHANGE);
    }

    @Bean
    public FanoutExchange greetingFanoutExchange() {
        return new FanoutExchange(GREETING_FANOUT_EXCHANGE);
    }
    @Bean
    public Binding greetingPtBrTopicBinding(TopicExchange exchange) {
        return  BindingBuilder.bind(greetingPtBrQueue()).to(exchange).with("greeting.pt_br");
    }

    @Bean
    public Binding greetingEnTopicBinding(TopicExchange exchange) {
        return  BindingBuilder.bind(greetingEnQueue()).to(exchange).with("greeting.en");
    }

    @Bean
    public Binding greetingNotificationTopicBinding(TopicExchange exchange) {
        return  BindingBuilder.bind(greetingNotificationQueue()).to(exchange).with("greeting.#");
    }

    @Bean
    public Binding greetingPtBrFanoutBinding(FanoutExchange exchange) {
        return  BindingBuilder.bind(greetingPtBrQueue()).to(exchange);
    }

    @Bean
    public Binding greetingEnFanoutBinding(FanoutExchange exchange) {
        return  BindingBuilder.bind(greetingEnQueue()).to(exchange);
    }

    @Bean
    public Binding greetingNotificationFanoutBinding(FanoutExchange exchange) {
        return  BindingBuilder.bind(greetingNotificationQueue()).to(exchange);
    }
}
