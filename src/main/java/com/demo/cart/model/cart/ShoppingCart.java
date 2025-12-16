package com.demo.cart.model.cart;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "carts")
public class ShoppingCart {
  @Id
  @NotBlank
  private String id;

  @NotNull
  @Indexed(unique = true)
  private String customerId;

  @NotNull
  private Instant createdAt;

  private Instant updatedAt;

  @Valid
  private List<CartItem> items = new ArrayList<>();

}
