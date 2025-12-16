package com.demo.cart.controller.dto.request;

import com.demo.cart.model.cart.Action;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ModifyItemRequestDto {

  @NotNull
  Long itemId;
  @NotNull
  Long oldOfferId;
  @NotNull
  Long newOfferId;
  Action action;

}
