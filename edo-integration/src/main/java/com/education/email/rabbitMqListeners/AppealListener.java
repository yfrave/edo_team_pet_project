package com.education.email.rabbitMqListeners;

import com.education.email.service.EmailService;
import com.education.model.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import com.education.model.dto.AppealDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableRabbit
@AllArgsConstructor
@Log4j2
public class AppealListener {
    EmailService emailService;
    final private AmqpTemplate template;

    /**
     * Рассылка уведомлений для подписантов и участников обращения.
     * @param appealDto
     */

    @RabbitListener(queues = "email.about.appeal")
    public void sendEmailForAppeal(AppealDto appealDto) {
        List<EmployeeDto> employeeDto = appealDto.getAddressee();
        List<EmployeeDto> signers = appealDto.getSigner();
        String message = "Добрый день, "
                + "name" + "!\n"
                + "message"
                + appealDto.getNumber() + ".\n"
                + "api/rest/appeal/"
                + appealDto.getId()
                + "\n";
        for (EmployeeDto employee : employeeDto) {
            emailService.sendSimpleEmail(employee.getEmail()
                    , employee.getFirstName()
                    , message.replace("name"
                                    , employee.getFirstName()
                                            + " " + employee.getLastName()
                                            + " " + employee.getMiddleName())
                            .replace("message"
                                    , "Для вас адресовано обращение с номером "));
            template.convertAndSend("notification.about.email", message, employee);
        }
        for (EmployeeDto employeeSigner : signers) {
            emailService.sendSimpleEmail(employeeSigner.getEmail()
                    , employeeSigner.getFirstName()
                    , message.replace("name"
                                    , employeeSigner.getFirstName()
                                            + " " + employeeSigner.getLastName()
                                            + " " + employeeSigner.getMiddleName())
                            .replace("message"
                                    , "Вы являетесь подписантом в обращении с номером "));
            template.convertAndSend("notification.about.email", message, employeeSigner);
        }

        log.log(Level.INFO, "Сообщение отправлено.");
    }

}
