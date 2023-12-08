package com.pivovarit.domain.rental;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Slf4j
record RabbitMqMessagePublisher(RabbitTemplate rabbitTemplate) implements MessagePublisher {

    @Override
    public void send(RentalEvent event) {
        log.info("sending {} via rmq", event);
        rabbitTemplate.convertAndSend("rentals-topic", event.getClass().getName(), event);
    }
}
