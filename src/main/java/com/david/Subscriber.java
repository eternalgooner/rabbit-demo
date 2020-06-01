package com.david;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class Subscriber implements MessageListener {

    @Autowired
    RabbitConnection rabbitConnection;

    private Connection connection;
    private Channel channel;

    public void onMessage(Message message) {

        System.out.println("SUBSCRIBER: ++ message received: " + message);
    }

    @PostConstruct
    public void listening() throws IOException {
        connection = rabbitConnection.getConnection();
        channel = connection.createChannel();

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("SUBSCRIBER: ++ message received: " + message);
        };
        channel.basicConsume(RabbitSetup.QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
