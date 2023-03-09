package com.education.email.rabbitMqListeners;

import com.education.email.service.EmailService;
import com.education.model.dto.AuthorDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import com.education.model.dto.AppealDto;
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
    AuthorDto authorDto;

    @RabbitListener(queues = "email.about.appeal")
    public void sendEmailForAppeal(AppealDto appealDto) {
        List<String> emails = appealDto
                .getAuthors()
                .stream()
                .map(AuthorDto::getEmail)
                .toList();
        String message = "Добрый день, "
                + appealDto.getCreator().getFirstName() + "!\n"
                + "Вы являетесь Подписантом в обращении с номером "
                + appealDto.getNumber() + ".\n"
                + "api/rest/appeal/"
                + appealDto.getId()
                + "\n";
        for (String email:  emails
             ) {
            emailService.sendSimpleEmail(email,
                    appealDto.getCreator().getFirstName()
                    ,message );
        }

        log.log(Level.INFO, "Сообщение отправлено. " + appealDto
                .getAuthors().stream().
                map(AuthorDto::getFirstName).toList());
    }

}
