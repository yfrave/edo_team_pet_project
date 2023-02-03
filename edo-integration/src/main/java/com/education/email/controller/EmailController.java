package com.education.email.controller;

import com.education.email.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/integration/email")
@Log4j2
public class EmailController {
    private EmailService emailService;

    @GetMapping("/send/{user-email}")
    public @ResponseBody ResponseEntity sendEmailWithAttachment(@PathVariable("user-email") String email) {
        try {
            emailService.sendEmailWithAttachment(email, "Example theme", "Example message", null);
        } catch (MailException | MessagingException | FileNotFoundException ex) {
//            log.error("Error while sending out email..{}", (Object) ex.getStackTrace());
            return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Please check your inbox", HttpStatus.OK);
    }
}
