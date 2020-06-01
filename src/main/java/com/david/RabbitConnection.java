package com.david;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitConnection {

    //create connection to be used in setup
    private Connection connection;

    @PostConstruct
    public void setup() {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
             connection = factory.newConnection();
        } catch (Exception ex){
            System.out.println("Caught exc: " + ex.getMessage());
        }
    }

    public Connection getConnection(){
        return connection;
    }

}
