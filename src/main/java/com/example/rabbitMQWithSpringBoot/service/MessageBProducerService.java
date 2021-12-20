package com.example.rabbitMQWithSpringBoot.service;

import com.example.rabbitMQWithSpringBoot.model.MessageA;
import com.example.rabbitMQWithSpringBoot.model.MessageB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageBProducerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageBProducerService.class);

    private static final String QUEUE_B = "${example.rabbitmq.queue.b}";

    @Value("${example.rabbitmq.routingkey.b}")
    private String routingNameB;

    @Value("${example.rabbitmq.exchange}")
    private String exchangeName;


    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sendToQueueB(MessageB messageB) {
        LOGGER.info("sendToQueueB: Notification with id: {} is ready to sent to Queue: {}", QUEUE_B, messageB.getId());

        rabbitTemplate.convertAndSend(exchangeName, routingNameB, messageB);
    }
}
