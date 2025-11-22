//package com.matheusdev.store.mappers;
//
//import com.matheusdev.store.dtos.ProductDto;
//import com.matheusdev.store.dtos.UserDto;
//import com.matheusdev.store.enteties.Product;
//import com.matheusdev.store.enteties.User;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Component;
//
//@Component
//@Primary
//public class ProductMapperManual implements  ProductMapper{
//    @Override
//    public ProductDto toDto(Product product) {
//        if (product == null) return null;
//
//        return new ProductDto(product.getId(), product.getName(), product.getStock(), product.getPrice(), product.getDescription(), product.getCategoryId());
////        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), LocalDateTime.now());
//    }
//}
