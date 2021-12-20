package com.example.rabbitMQWithSpringBoot.service;

import com.example.rabbitMQWithSpringBoot.model.MessageA;
import com.example.rabbitMQWithSpringBoot.model.MessageB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListenerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQListenerService.class);

    private static final String QUEUE_A = "${example.rabbitmq.queue.a}";
    private static final String QUEUE_B = "${example.rabbitmq.queue.b}";

    @RabbitListener(queues = QUEUE_A)
    public void handleMessageQueueA(MessageA messageA) {

        LOGGER.info("handleMessageQueue1: New notification arrived from Queue: {}. Notification: {}", QUEUE_A, messageA);
    }

    @RabbitListener(queues = QUEUE_B)
    public void handleMessageQueueB(MessageB messageB) {

        messageB.setSeen(true);

        LOGGER.info("handleMessageQueueB: New notification arrived from Queue: {}. Notification: {}", QUEUE_B, messageB);
    }
}
