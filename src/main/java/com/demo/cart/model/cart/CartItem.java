package com.demo.cart.model.cart;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Builder
public class CartItem {
  @NotBlank
  private String id;

  @NotNull
  private Long itemId;
  @NotNull
  private Long offerId;

  private Long oldOfferId;

  @Valid
  @NotNull
  private Action action;

  private Integer quantity;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CartItem cartItem = (CartItem) o;
    return Objects.equals(itemId, cartItem.itemId) && Objects.equals(offerId, cartItem.offerId) && action == cartItem.action;
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemId, offerId, action);
  }
}
