package com.education.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация RabbitMQ. Пример создания двух очередей и связывание их с routing key
 */
@Configuration
public class RabbitTemplateConfiguration {

    @Value(value = "${spring.rabbitmq.exchange}")
    private String exchange;

    @Value(value = "${spring.rabbitmq.queues.addressCreate}")
    private String nameQueue;

    @Value("${spring.rabbitmq.queues.addressCreate}")
    private String routingKey;


    @Bean
    public Queue addressCreate(){
        return new Queue(nameQueue, false);
    }

    @Bean
    public DirectExchange exchange(){
        return new DirectExchange(exchange);
    }

    @Bean
    public Binding binding(Queue addressCreate, DirectExchange exchange){
        return BindingBuilder
                .bind(addressCreate)
                .to(exchange)
                .with(routingKey);
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

}
