package com.matheusdev.store.mappers;

import com.matheusdev.store.dtos.ProductDto;
import com.matheusdev.store.enteties.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "categoryId", source = "categoryId")
    ProductDto toDto(Product product);
}
