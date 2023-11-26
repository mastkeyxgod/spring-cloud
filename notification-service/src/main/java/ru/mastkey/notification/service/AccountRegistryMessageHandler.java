package ru.mastkey.notification.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.mastkey.notification.config.RabbitMQConfigRegistry;

@Service
public class AccountRegistryMessageHandler {
    private final JavaMailSender javaMailSender;

    @Autowired
    public AccountRegistryMessageHandler(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @RabbitListener(queues = RabbitMQConfigRegistry.QUEUE_ACCOUNT_REGISTRY)
    public void receive(Message message) throws JsonProcessingException {
        System.out.println(message);
        byte[] body = message.getBody();
        String jsonBody = new String(body);
        ObjectMapper objectMapper = new ObjectMapper();

        RegistryNotify registryNotify = objectMapper.readValue(jsonBody, RegistryNotify.class);


        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(registryNotify.getEmail());
        mailMessage.setFrom("asdasd");
        mailMessage.setSubject("ИлюхаBank");
        mailMessage.setText(String.format("Dear %s, thank you for registration in our bank!",
                registryNotify.getName()));
        System.out.println("asdasdsadsadsadasdsadsadsd");

        try {
            javaMailSender.send(mailMessage);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
