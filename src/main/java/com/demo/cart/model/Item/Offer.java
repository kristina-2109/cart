package com.demo.cart.model.Item;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Offer {
  private String id;
  @NotBlank
  private Long offerId;
  @NotNull
  private String offerName;
  @Valid
  @NotNull
  private Price price;
}
