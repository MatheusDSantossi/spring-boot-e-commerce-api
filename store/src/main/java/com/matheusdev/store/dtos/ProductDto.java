package com.matheusdev.store.dtos;

import com.matheusdev.store.enteties.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private Integer stock;
    private BigDecimal price;
    private String description;
    private Byte categoryId;
}
