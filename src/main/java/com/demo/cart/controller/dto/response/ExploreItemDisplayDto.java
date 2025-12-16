package com.demo.cart.controller.dto.response;

import com.demo.cart.model.Item.ItemType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class ExploreItemDisplayDto {

  private String name;
  @JsonProperty("category")
  private ItemType type;
  private List<OfferDisplayDto> offers;

}
