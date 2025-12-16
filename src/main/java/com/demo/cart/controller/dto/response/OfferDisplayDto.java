package com.demo.cart.controller.dto.response;

import com.demo.cart.controller.dto.response.cart.PriceDisplayDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class OfferDisplayDto {

  @JsonProperty("name")
  private String offerName;

  private PriceDisplayDto price;

}
