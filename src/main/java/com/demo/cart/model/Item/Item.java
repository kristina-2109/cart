package com.demo.cart.model.Item;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Data
@ToString
@Document(collection = "items")
public class Item {

  @Id
  private String id;

  private Long itemId;

  @NotNull
  private String name;

  @NotNull
  @Valid
  private ItemType type;

  @Valid
  @NotEmpty
  private List<Offer> offers;
}
