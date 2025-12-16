package com.demo.cart.controller.dto.response.cart;

import com.demo.cart.model.Item.Amount;
import com.demo.cart.model.Item.PriceType;
import com.demo.cart.model.Item.Recurrence;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class PriceDisplayDto {

  private PriceType type;
  private Amount amount;
  private Recurrence recurrence;

}
