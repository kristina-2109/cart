package com.demo.cart.service.mapper;

import com.demo.cart.model.cart.CartItem;
import com.demo.cart.model.statistics.StatisticsEntity;
import com.demo.cart.service.statistic.CartEvent;
import java.sql.Timestamp;
import java.time.Instant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CartEventMapper {

  public CartEvent toCartEvent(CartItem item) {
    return CartEvent.builder()
        .itemId(item.getItemId())
        .offerId(item.getOfferId())
        .action(item.getAction())
        .time(Timestamp.from(Instant.now()))
        .quantity(item.getQuantity())
        .build();
  }

  public StatisticsEntity toStatisticsEntity(CartEvent event) {
    return StatisticsEntity.builder()
        .itemId(event.getItemId())
        .offerId(event.getOfferId())
        .action(event.getAction())
        .quantity(event.getQuantity())
        .time(event.getTime())
        .build();
  }
}

