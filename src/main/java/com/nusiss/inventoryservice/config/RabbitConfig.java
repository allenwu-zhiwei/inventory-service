package com.nusiss.inventoryservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String ORDER_QUEUE = "order.queue";
    public static final String INVENTORY_QUEUE = "inventory.queue";
    public static final String CONFIRM_QUEUE = "confirm.queue";
    public static final String ROLLBACK_QUEUE = "rollback.queue";
    public static final String EXCHANGE = "order.exchange";

    @Bean
    public Queue orderQueue() {
        return new Queue(ORDER_QUEUE, true);
    }

    @Bean
    public Queue inventoryQueue() {
        return new Queue(INVENTORY_QUEUE, true);
    }

    @Bean
    public Queue confirmQueue() {
        return new Queue(CONFIRM_QUEUE, true);
    }

    @Bean
    public Queue rollbackQueue() {
        return new Queue(ROLLBACK_QUEUE, true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding orderBinding(Queue orderQueue, TopicExchange exchange) {
        return BindingBuilder.bind(orderQueue).to(exchange).with("order.*");
    }

    @Bean
    public Binding inventoryBinding(Queue inventoryQueue, TopicExchange exchange) {
        return BindingBuilder.bind(inventoryQueue).to(exchange).with("inventory.*");
    }

    @Bean
    public Binding confirmBinding(Queue confirmQueue, TopicExchange exchange) {
        return BindingBuilder.bind(confirmQueue).to(exchange).with("confirm.*");
    }

    @Bean
    public Binding rollbackBinding(Queue rollbackQueue, TopicExchange exchange) {
        return BindingBuilder.bind(rollbackQueue).to(exchange).with("rollback.*");
    }
}

