package com.demo.cart.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseDto {

  Long timestamp;
  String message;

}
