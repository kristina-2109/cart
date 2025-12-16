package com.demo.cart.controller.dto.response.cart;

import com.demo.cart.model.cart.Action;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class CartItemDisplayDto {

  private String cartItemName;
  private String oldOfferName;
  private PriceDisplayDto price;
  private Integer quantity;
  private Action action;

}
