package ru.mastkey.notification.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfigRegistry {

    public static final String QUEUE_ACCOUNT_REGISTRY = "js.accountRegistry.notify";
    public static final String TOPIC_EXCHANGE_ACCOUNT_REGISTRY = "js.accountRegistry.notify.exchange";
    public static final String ROUTING_KEY_ACCOUNT_REGISTRY = "js.key.accountRegistry";

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Bean
    public TopicExchange accountRegistryExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_ACCOUNT_REGISTRY);
    }

    @Bean
    public Queue queueAccountRegistry() {
        return new Queue(QUEUE_ACCOUNT_REGISTRY);
    }

    @Bean
    public Binding accountRegistryBinding() {
        return BindingBuilder
                .bind(queueAccountRegistry())
                .to(accountRegistryExchange())
                .with(ROUTING_KEY_ACCOUNT_REGISTRY);
    }

}
