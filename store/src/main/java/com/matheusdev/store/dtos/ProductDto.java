package com.matheusdev.store.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matheusdev.store.enteties.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
//    @JsonIgnore
    private Long id;

    private String name;
    private Integer stock;
    private BigDecimal price;
    private String description;
    private Long categoryId;
}
