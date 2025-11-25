package com.matheusdev.store.mappers;

import com.matheusdev.store.dtos.ProductDto;
import com.matheusdev.store.enteties.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "categoryId", source = "categoryId")
    ProductDto toDto(Product product);

    Product toEntity(ProductDto product);

    @Mapping(target = "id", ignore = true)
    void update(ProductDto request, @MappingTarget Product product);
}
