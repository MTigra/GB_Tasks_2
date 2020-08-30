package com.geekbrains.rabbitmqapp;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainApp {
    private static String EXCHANGE_ORDERS_TO_PROCESS = "ordersToProcess";
    private static String EXCHANGE_PROCESSED_ORDERS = "processedOrders";
    private static List<Delivery> listOfOrdersInProccessDeliverys = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        initShowOrders();

        boolean exit = false;
        while (!exit) {
            showOrders();
            exit = processUserInput();
        }
        System.exit(0);
    }

    private static boolean processUserInput() throws IOException, TimeoutException {
        Connection connection = RabbitMQConnectionFactory.getConnectionFactory().newConnection();
        Channel channel = connection.createChannel();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("exit")) {
            return true;
        }
        Pattern inputPattern = Pattern.compile("/готово (\\d+)");
        Matcher inputMatcher = inputPattern.matcher(input);
        if (inputMatcher.matches()) {
            Long id = Long.parseLong(inputMatcher.group(1));
            Delivery delivery = listOfOrdersInProccessDeliverys.stream()
                    .filter(del -> {
                        try {
                            return new String(del.getBody(), "UTF-8").equals(id.toString());
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        return false;
                    })
                    .findFirst().orElseThrow(IllegalArgumentException::new);
            channel.basicPublish(EXCHANGE_PROCESSED_ORDERS, "", null, id.toString().getBytes("UTF-8"));
            System.out.println("попали вобработку");
//            Channel ackChannel = connection.createChannel();
//            ackChannel.exchangeDeclare(EXCHANGE_ORDERS_TO_PROCESS, BuiltinExchangeType.FANOUT,true);
//            ackChannel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            listOfOrdersInProccessDeliverys.remove(delivery);
        }
        return false;
    }

    private static void initShowOrders() throws IOException, TimeoutException {
        Connection connection = RabbitMQConnectionFactory.getConnectionFactory().newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_ORDERS_TO_PROCESS, BuiltinExchangeType.FANOUT, true);

        String queueName = channel.queueDeclare("ordersQueue", true, false, false, null).getQueue();
        System.out.println("My queue name: " + queueName);
        channel.queueBind(queueName, EXCHANGE_ORDERS_TO_PROCESS, "");
        System.out.println("Waiting for orders on " + EXCHANGE_ORDERS_TO_PROCESS + ", queue: " + queueName);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            listOfOrdersInProccessDeliverys.add(delivery);
            System.out.println(" [x] New order: id: " + new String(delivery.getBody(),StandardCharsets.UTF_8));
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }

    private static void showOrders() {
        if (listOfOrdersInProccessDeliverys.isEmpty()) {
            return;
        }
        System.out.println("List of not processed orders:");

        listOfOrdersInProccessDeliverys.forEach(delivery -> {
            System.out.println("Id: " + new String(delivery.getBody(), StandardCharsets.UTF_8));
        });

    }


}
