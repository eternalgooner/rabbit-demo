package com.david;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Scanner;

//this object will publish messages
@Component
public class Publisher {

    @Autowired
    RabbitConnection rabbitConnection;

    private Connection connection;
    private Channel channel;

    //channel args
    private static String EXCHANGE = RabbitSetup.EXCHANGE_NAME;
    private static String ROUTING_KEY = RabbitSetup.ROUTING_KEY;
    private static AMQP.BasicProperties PROPS = null;

    public void publish(String message) throws IOException {
        channel.basicPublish(EXCHANGE, ROUTING_KEY, PROPS, message.getBytes());
        System.out.println("PUBLISHER: --  [x] Sent '" + message + "'");
    }

    @PostConstruct
    public void userInput() throws IOException {
        connection = rabbitConnection.getConnection();
        channel = connection.createChannel();

        getMessages();
    }

    private void getMessages() throws IOException {
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("PUBLISHER: -- please enter a message to send to rabbit...\n");
            String message = scanner.nextLine();

            publish(message);
        }
    }
}
