package com.mvpmatch.vending_machine.mapper;

import com.mvpmatch.vending_machine.dto.ProductDto;
import com.mvpmatch.vending_machine.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product dtoToEntity(ProductDto productDto);

    @Mapping(source = "user", target = "createdBy")
    ProductDto entityToDto(Product product);

    List<Product> dtosToEntities(List<ProductDto> productDtos);

    List<ProductDto> entitiesToDtos(List<Product> products);
}
