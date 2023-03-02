package com.example.coinmonitoring.config;


import com.example.coinmonitoring.exception.EmailException;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class MailConfig {

  @Bean
  public Message configMessage() throws MessagingException {
    Message message = new MimeMessage(configSession());
    message.setFrom(new InternetAddress("luiserkges@gmail.com"));
    return message;
  }

  @Bean
  public Session configSession() {
    try {
      Properties props = new Properties();
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.socketFactory.port", "465");
      props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.port", "465");

      Session session = Session.getDefaultInstance(props,
          new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication("mlindencamp@gmail.com", "pgjsuplgllmockcu");
            }
          });

      session.setDebug(false);

      return session;
    }catch (Exception ex){
      throw new EmailException("Error on login");
    }

  }
}
