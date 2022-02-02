package com.mvpmatch.vending_machine.controller;

import com.mvpmatch.vending_machine.dto.ProductDto;
import com.mvpmatch.vending_machine.entity.User;
import com.mvpmatch.vending_machine.mapper.ProductMapper;
import com.mvpmatch.vending_machine.mapper.UserMapper;
import com.mvpmatch.vending_machine.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import com.mvpmatch.vending_machine.entity.Product;
import com.mvpmatch.vending_machine.service.ProductService;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/product", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(UserService userService, UserMapper userMapper, ProductService productService, ProductMapper productMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping
    ResponseEntity<List<ProductDto>> findAll(@RequestParam("size") Integer rows) {
        Page<Product> pagedProducts = productService.findAll(Pageable.ofSize(rows));
        List<ProductDto> productDtos = productMapper.entitiesToDtos(pagedProducts.getContent());

        return new ResponseEntity(productDtos, HttpStatus.OK);
    }

    @PostMapping("/create")
    ResponseEntity<ProductDto> create(@Valid @RequestBody ProductDto productDto) {
        User user = userService.findUserByUsername(productDto.getCreatedBy().getUsername());

        productService.create(productMapper.dtoToEntity(productDto), user);

        productDto.setCreatedBy(userMapper.entityToDto(user));

        return new ResponseEntity(productDto, HttpStatus.CREATED);
    }
}
