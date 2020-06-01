package com.david;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;

@Component
public class RabbitSetup {

    @Autowired
    RabbitConnection rabbitConnection;

    @Autowired
    Subscriber subscriber;

    //queue name
    public static String QUEUE_NAME = "rabbit-demo-q";

    //queue args
    private static boolean DURABLE = false;
    private static boolean EXCLUSIVE = false;
    private static boolean AUTO_DELETE = false;
    private static Map QUEUE_ARGS = null;

    public static String ROUTING_KEY = "rabbit-demo-key";

    //exchange args
    public static String EXCHANGE_NAME = "rabbit-demo-exchange";
    private static String EXCHANGE_TYPE = "direct";

    @PostConstruct
    public void setupExchangeAndQueue() throws IOException {
        Connection connection = rabbitConnection.getConnection();
        Channel channel = connection.createChannel();

        //declare exchange
        channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);

        //declare queue
        channel.queueDeclare(QUEUE_NAME, DURABLE, EXCLUSIVE, AUTO_DELETE, QUEUE_ARGS);

        //bind queue to exchange
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);

    }
}
