package ru.mastkey.notification.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.mastkey.notification.config.RabbitMQConfigBills;

@Service
public class BillRegistryMessageHandler {

    private final JavaMailSender javaMailSender;

    @Autowired
    public BillRegistryMessageHandler(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }



    @RabbitListener(queues = RabbitMQConfigBills.QUEUE_BILL_REGISTRY)
    public void receive(Message message) throws JsonProcessingException {
        byte[] body = message.getBody();
        String jsonBody = new String(body);
        ObjectMapper objectMapper = new ObjectMapper();

        BillRegistryNotify billRegistryNotify = objectMapper.readValue(jsonBody, BillRegistryNotify.class);


        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(billRegistryNotify.getEmail());
        mailMessage.setFrom("asdasd");
        mailMessage.setSubject("ИлюхаBank");
        mailMessage.setText(String.format("Dear %s, You have created a new bill with id %d and amount %s",
                billRegistryNotify.getName(), billRegistryNotify.getBillId(), billRegistryNotify.getAmount().toString()));
        try {
            javaMailSender.send(mailMessage);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
