package com.example.demo.ampq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class Consumer {
    private Logger logger = LoggerFactory.getLogger(Consumer.class);

    @RabbitListener(queues="orderServiceQueue")
    public void receive(String message) {
        logger.info(message);
    }
}
