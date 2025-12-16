package com.demo.cart.service.statistic;

import com.demo.cart.model.cart.Action;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartEvent {

  private Long offerId;
  private Long itemId;
  private Action action;
  private Integer quantity;
  private Timestamp time;

}
