package com.example.rabbitMQWithSpringBoot.controller;

import com.example.rabbitMQWithSpringBoot.model.MessageB;
import com.example.rabbitMQWithSpringBoot.service.MessageBProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rabbitmq/")
public class RabbitMQWebController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQWebController.class);

    @Autowired
    private MessageBProducerService messageBProducerService;

    @GetMapping(value = "/messageBProducer")
    public ResponseEntity<MessageB> messageBProducer(@RequestParam("messageId") String messageId,
                                             @RequestParam("message") String message) {

        MessageB messageB = MessageB.builder()
                .id(messageId)
                .message(message)
                .seen(false)
                .build();


        LOGGER.info("messagebProducer: new notification: {} arrived", messageB);

        messageBProducerService.sendToQueueB(messageB);

        return ResponseEntity.ok(messageB);
    }

}
