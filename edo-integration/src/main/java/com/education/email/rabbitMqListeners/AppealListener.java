package com.education.email.rabbitMqListeners;

import com.education.email.service.EmailService;
import com.education.model.dto.EmployeeDto;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpHost;
import org.apache.logging.log4j.Level;
import com.education.model.dto.AppealDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

@Service
@EnableRabbit
@AllArgsConstructor
@Log4j2
public class AppealListener {
    EmailService emailService;
    final private AmqpTemplate template;

    private final EurekaClient EUREKA_CLIENT;

    private final String BASE_URL = "/api/rest/appeal";
    private final String SERVICE_NAME = "edo-rest";


    /**
     * Получает инстанс случайным методом
     */
    private InstanceInfo getInstance() {
        List<InstanceInfo> instances = EUREKA_CLIENT.getApplication(SERVICE_NAME).getInstances();
        return instances.get((int) (instances.size() * Math.random()));
    }

    private URI getURIByInstance(InstanceInfo instanceInfo, String pathVariable) {
        return UriComponentsBuilder.fromPath(BASE_URL + pathVariable)
                .scheme(HttpHost.DEFAULT_SCHEME_NAME)
                .host(instanceInfo.getHostName())
                .port(instanceInfo.getPort())
                .buildAndExpand(pathVariable)
                .toUri();
    }

    /**
     * Рассылка уведомлений для подписантов и участников обращения.
     *
     * @param appealDto
     */

    @RabbitListener(queues = "email.about.appeal")
    public void sendEmailForAppeal(AppealDto appealDto) throws MalformedURLException {

        String message = "Добрый день, name!\n message"
                + appealDto.getNumber() + ".\n"
                + getURIByInstance(getInstance(), "/byId/" + appealDto.getId()).toURL();
        messageAssembly(appealDto.getAddressee(), message, "Для вас адресовано обращение с номером ");
        messageAssembly(appealDto.getSigner(), message, "Вы являетесь подписантом в обращении с номером ");

        log.log(Level.INFO, "Сообщение отправлено.");
    }

    public void messageAssembly(List<EmployeeDto> employees, String message, String replaceText) {

        for (EmployeeDto employee : employees) {
            emailService.sendSimpleEmail(employee.getEmail()
                    , employee.getFirstName()
                    , message.replace("name"
                                    , employee.getFioNominative())
                            .replace("message", replaceText));
            template.convertAndSend("notification.about.email", message, employee);
        }
    }

}
