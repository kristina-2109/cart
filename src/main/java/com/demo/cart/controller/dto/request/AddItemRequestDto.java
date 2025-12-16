package com.demo.cart.controller.dto.request;

import com.demo.cart.model.cart.Action;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AddItemRequestDto {

  @NotNull
  Long itemId;
  @NotNull
  Long offerId;
  Action action;
  @NotNull
  Integer quantity;

}
