package com.demo.cart.model.Item;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Price {

  @NotNull
  private PriceType type;

  @NotNull
  @Valid
  private Amount amount;

  @Valid
  private Recurrence recurrence;
}
