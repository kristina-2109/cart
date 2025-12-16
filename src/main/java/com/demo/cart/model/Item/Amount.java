package com.demo.cart.model.Item;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record Amount(@NotBlank String currency, @NotNull @DecimalMin("0.0") BigDecimal value) {

}
