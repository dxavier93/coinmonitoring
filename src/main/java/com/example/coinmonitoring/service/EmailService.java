package com.example.coinmonitoring.service;


import com.example.coinmonitoring.exception.EmailException;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
@Slf4j
public class EmailService {
  @Autowired
  private Message message;

  public void sendMail(String userMail, String emailMessage) {
    Message message = buildMessage(emailMessage, userMail);

    sendMailWithMessage(message);
  }

  public void sendMail(String userName, String userMail, long orderId, long quantity,
                       LocalDateTime creationDate, String itemName) {
    String emailText = "Dear user " + userName + ". your order with id: [" + orderId + "] " +
        "according to " + quantity + " x " + itemName + " was completed at [" + creationDate + "].";

    buildMessage(emailText, userMail);

    sendMailWithMessage(message);
  }

  private Message buildMessage(String text, String mailAddress) {
    try {
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailAddress));
      message.setSubject("Completed order");
      message.setText(text);
      return message;
    } catch (Exception ex) {
      throw new EmailException("Error at send email.");
    }

  }

  private void sendMailWithMessage(Message message) {
    try {
      log.info("Sending email message");
      log.info(message.getSubject());
      Transport.send(message);
      log.info("Email message sent with success");
    } catch (Exception ex) {
      log.error("Error at sent message: " + ex.getMessage());
      throw new EmailException("Error at send email.");
    }
  }
}
