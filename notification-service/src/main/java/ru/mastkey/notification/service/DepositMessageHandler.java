package ru.mastkey.notification.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.mastkey.notification.config.RabbitMQConfig;

@Service
public class DepositMessageHandler {
    private final JavaMailSender javaMailSender;

    @Autowired
    public DepositMessageHandler(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_DEPOSIT)
    public void receive(Message message) throws JsonProcessingException {
        System.out.println(message);
        byte[] body = message.getBody();
        String jsonBody = new String(body);
        ObjectMapper objectMapper = new ObjectMapper();

        DepositResponse depositResponse = objectMapper.readValue(jsonBody, DepositResponse.class);

        System.out.println(depositResponse);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(depositResponse.getEmail());
        mailMessage.setFrom("asdasd");
        mailMessage.setSubject("ИлюхаBank");
        mailMessage.setText(String.format("Make deposit on your bill with id %s, sum: %s\n" +
                        "New balance: %s",
                depositResponse.getBillId().toString(),
                depositResponse.getAmount(),
                depositResponse.getNewBalance()));

        try {
            javaMailSender.send(mailMessage);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
