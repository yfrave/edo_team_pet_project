package com.education.service;

import com.education.entity.Address;
import com.education.model.dto.AddressDto;
import com.education.model.constant.RabbitConstant;
import com.education.model.dto.EmployeeDto;
import com.education.model.dto.NotificationDto;
import com.education.repository.AddressRepository;
import com.education.service.appeal.AppealService;
import com.education.service.notification.NotificationService;
import com.education.util.Mapper.impl.AddressMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Пример RabbitMQ Listener
 * Над методом необходимо указать аннотацию @RabbitListener с названием очереди
 */
@Service
@EnableRabbit
@AllArgsConstructor
@Log4j2
public class RabbitRepoListenerExample {
    private AddressRepository addressRepository;
    private NotificationService notificationService;
    private AddressMapper mapper;

    @RabbitListener(queues = RabbitConstant.addressCreateDBQueue)
    public void createAddress(AddressDto addressDto) {

        Address address = mapper.toEntity(addressDto);
        addressRepository.saveAndFlush(address);

        log.log(Level.INFO, "Сущность сохранена " + addressDto.toString());

    }

    /**
     * Listener сохранения notification
     *
     * @param message
     * @param employeeDto
     */
    @RabbitListener(queues = RabbitConstant.notificationAboutEmail)
    public void sendNotification(String message, EmployeeDto employeeDto) {
        NotificationDto notification = new NotificationDto();
        notification.setCreationDate((ZonedDateTime.now(ZoneId.of("Europe/Moscow"))));
        notification.setEmployeeDto(employeeDto);
        notification.setMessage(message);
        notificationService.save(notification);
        log.log(Level.INFO, "Сохранена нотификация для пользователя c id"
                + employeeDto.getId());
    }
}