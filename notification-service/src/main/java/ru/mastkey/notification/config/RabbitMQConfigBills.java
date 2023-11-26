package ru.mastkey.notification.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfigBills {
    public static final String QUEUE_BILL_REGISTRY = "js.billRegistry.notify";
    public static final String TOPIC_EXCHANGE_BILL_REGISTRY = "js.billRegistry.notify.exchange";
    public static final String ROUTING_KEY_BILL_REGISTRY = "js.key.billRegistry";

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Bean
    public TopicExchange billRegistryExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_BILL_REGISTRY);
    }

    @Bean
    public Queue queueBillRegistry() {
        return new Queue(QUEUE_BILL_REGISTRY);
    }

    @Bean
    public Binding billRegistryBinding() {
        return BindingBuilder
                .bind(queueBillRegistry())
                .to(billRegistryExchange())
                .with(ROUTING_KEY_BILL_REGISTRY);
    }
}
