package com.geekbrains.book.store.config;

import com.geekbrains.book.store.receivers.OrderProcessedReceiver;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class RabbitMQConfig {

    private static String EXCHANGE_PROCESSED_ORDERS = "processedOrders";

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_PROCESSED_ORDERS);
    }



    private static class ReceiverConfig {
        @Bean
        public Queue queue1() {
            return new Queue("processedOrdersQueue", true);
        }


        @Bean
        public Binding binding1(FanoutExchange fanout,
                                Queue queue1) {
            return BindingBuilder.bind(queue1).to(fanout);
        }

        @Bean
        public OrderProcessedReceiver receiver() {
            return new OrderProcessedReceiver();
        }
    }
}
