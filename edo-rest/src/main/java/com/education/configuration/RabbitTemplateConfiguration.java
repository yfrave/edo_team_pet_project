package com.education.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация RabbitMQ. Пример создания двух очередей и связывание их с routing key
 */
@Configuration
public class RabbitTemplateConfiguration {

    @Bean
    public Queue queue(){
        return new Queue("address.create.service", false);
    }

    @Bean
    public Queue queue1(){
        return new Queue("address.create.service1", false);
    }

    @Bean
    public DirectExchange exchange(){
        return new DirectExchange("edo.direct");
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange){
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("address.create.service");
    }

    @Bean
    public Binding binding1(Queue queue1, DirectExchange exchange){
        return BindingBuilder
                .bind(queue1)
                .to(exchange)
                .with("address.create.service1");
    }


    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

}
