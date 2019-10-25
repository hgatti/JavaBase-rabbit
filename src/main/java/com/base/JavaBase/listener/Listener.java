package com.base.JavaBase.listener;

import com.base.JavaBase.persistence.entity.Message;
import com.base.JavaBase.persistence.repository.MessageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Listener {

    private static final Logger LOG = LoggerFactory.getLogger(Listener.class);

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MessageRepository messageRepository;

    @RabbitListener(queues = {"${queue.name}"})
    public void process(@Payload String message) {
        try {
            //String event = mapper.readValue(message, String.class); //caso necessario mapear para algum objeto.
            Message messagePersisted = new Message();
            messagePersisted.setMessage(message);

            messageRepository.save(messagePersisted);

            LOG.info("received={}",message);

        } catch (Exception e) {
            LOG.error("ERROR", e);
            throw new RuntimeException(e.getMessage());
        }
    }
}
