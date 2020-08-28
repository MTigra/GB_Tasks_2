package com.geekbrains.rabbitmqapp;

import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQConnectionFactory {
    static ConnectionFactory cf;

    public static ConnectionFactory getConnectionFactory() {
        if (cf == null) {
            cf = new ConnectionFactory();
            cf.setHost("localhost");
        }
        return cf;
    }
}
