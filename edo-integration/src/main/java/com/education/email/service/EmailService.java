package com.education.email.service;

import jakarta.mail.MessagingException;

import java.io.FileNotFoundException;

/**
 * Интерфейс EmailService.
 * Интерфейс для отправки email
 */
public interface EmailService {
    /**
     * Метод для отправки email без attachment
     * @param toAddress
     * @param subject
     * @param message
     */
    void sendSimpleEmail(String toAddress, String subject, String message);

    /**
     * Метод для отправки email
     * @param toAddress
     * @param subject
     * @param message
     */
    void sendEmailWithAttachment(String toAddress, String subject, String message, String attachment)
            throws MessagingException, FileNotFoundException;
}
