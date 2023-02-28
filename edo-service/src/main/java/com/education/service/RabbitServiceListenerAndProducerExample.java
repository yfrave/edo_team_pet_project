package com.education.service;

import com.education.model.dto.AddressDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class RabbitServiceListenerAndProducerExample {

    @Autowired
    private AmqpTemplate template;

    @RabbitListener(queues = "address.create.service")
    public void getMessage(AddressDto addressDto) {

        template.convertAndSend("edo.direct","address.create.DB", addressDto);
        log.log(Level.INFO, "Сущность принята и отправлена в DB " + addressDto.toString());


    }

}
