package com.education.email.controller;

import com.education.email.service.EmailService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@ApiModel("Класс EmailController - RestController для отправки email")
@RestController
@AllArgsConstructor
@RequestMapping("/api/integration/email")
@Log4j2
public class EmailController {
    @ApiModelProperty("emailService")
    private EmailService emailService;

    @ApiOperation("Отправка email")
    @GetMapping("/send/{user-email}")
    public @ResponseBody ResponseEntity sendEmailWithAttachment(@PathVariable("user-email") String email) {
        try {
            emailService.sendEmailWithAttachment(email, null, "Example message", null);
        } catch (MailException | MessagingException | FileNotFoundException ex) {
            log.error("Error while sending out email..{}", ex.getStackTrace());
            return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Please check your inbox", HttpStatus.OK);
    }
}
