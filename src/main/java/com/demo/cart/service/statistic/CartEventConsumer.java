package com.demo.cart.service.statistic;

import com.demo.cart.repository.StatisticsRepository;
import com.demo.cart.service.mapper.CartEventMapper;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartEventConsumer {

  private final StatisticsRepository statsRepo;
  private final CartEventMapper cartEventMapper;

  @KafkaListener(topics = "${app.kafka.topic.cart-events}")
  public void handleCartEvent(CartEvent event) {
    statsRepo.save(cartEventMapper.toStatisticsEntity(event));
  }
}
