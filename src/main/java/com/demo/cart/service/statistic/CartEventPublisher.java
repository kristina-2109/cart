package com.demo.cart.service.statistic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CartEventPublisher {

  private final KafkaTemplate<Long, CartEvent> kafkaTemplate;

  @Value("${app.kafka.topic.cart-events}")
  private String topic;

  public CartEventPublisher(KafkaTemplate<Long, CartEvent> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void publishEvent(CartEvent event) {
    kafkaTemplate.send(topic, event.getItemId(), event);
  }

}
