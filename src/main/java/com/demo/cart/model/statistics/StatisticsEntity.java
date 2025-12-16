package com.demo.cart.model.statistics;

import com.demo.cart.model.cart.Action;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document("statistics")
public class StatisticsEntity {

  @Id
  private String id;
  @NotNull
  private Long offerId;
  @NotNull
  private Long itemId;
  @NotNull
  private Action action;
  @NotNull
  @Min(1)
  private Integer quantity;
  @NotNull
  private Timestamp time;

}
