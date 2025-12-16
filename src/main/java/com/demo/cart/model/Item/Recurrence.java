package com.demo.cart.model.Item;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record Recurrence(@NotBlank String unit, @NotNull @Min(1) Integer count) {

}
