package com.trendbazaar.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductDto {

    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    private String description;

    @NotNull(message = "price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "price must be > 0")
    private BigDecimal price;

    private Integer stock;

    private String imageUrl;
}
