package com.mvpmatch.vending_machine.controller;

import com.mvpmatch.vending_machine.dto.PurchaseResponseDto;
import com.mvpmatch.vending_machine.dto.UserDto;
import com.mvpmatch.vending_machine.entity.Product;
import com.mvpmatch.vending_machine.entity.User;
import com.mvpmatch.vending_machine.enums.Denomination;
import com.mvpmatch.vending_machine.mapper.UserMapper;
import com.mvpmatch.vending_machine.service.ProductService;
import com.mvpmatch.vending_machine.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collections;

@RestController
@CrossOrigin
@RequestMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private static final String ERROR_RESPONSE_KEY = "errorMsg";

    private final UserService userService;
    private final ProductService productService;
    private final UserMapper userMapper;

    public UserController(UserService userService, ProductService productService, UserMapper userMapper) {
        this.userService = userService;
        this.productService = productService;
        this.userMapper = userMapper;
    }

    @PostMapping("/create")
    ResponseEntity<User> create(@Valid @RequestBody User user) {
        if (user.getDeposit() % 5 != 0) {
            return new ResponseEntity(Collections.singletonMap(ERROR_RESPONSE_KEY, "Initial deposit must be a value divisible by 5"), HttpStatus.BAD_REQUEST);
        }

        if (userService.findUserByUsername(user.getUsername()) == null) {
            return new ResponseEntity(Collections.singletonMap(ERROR_RESPONSE_KEY, "User already exist"), HttpStatus.BAD_REQUEST);
        }

        User userResponse = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PutMapping("/deposit/{username}/{amount}")
    ResponseEntity<User> deposit(@PathVariable("username") String username, @PathVariable("amount") Integer amount) {
        if (amount % 5 != 0) {
            return new ResponseEntity(Collections.singletonMap(ERROR_RESPONSE_KEY, "Amount to deposit must be a value divisible by 5"), HttpStatus.BAD_REQUEST);
        }

        User user = userService.findUserByUsername(username);
        if (user == null) {
            return new ResponseEntity(Collections.singletonMap(ERROR_RESPONSE_KEY, "Username does not exist"), HttpStatus.NOT_FOUND);
        }

        if (!"buyer".equalsIgnoreCase(user.getRole())) {
            return new ResponseEntity(Collections.singletonMap(ERROR_RESPONSE_KEY, "User is not authorized to perform a deposit"), HttpStatus.UNAUTHORIZED);
        }

        boolean amountIsAllowed = Arrays.stream(Denomination.values()).anyMatch(denom -> denom.getAmount() == amount);
        if (!amountIsAllowed) {
            return new ResponseEntity(Collections.singletonMap(ERROR_RESPONSE_KEY, "You can't deposit the specified amount. Valid amounts are 5, 10, 20, 50 and 100 cent"), HttpStatus.BAD_REQUEST);
        }

        User userResponse = userService.deposit(user, amount);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @PutMapping("/buy/{username}/{productId}/{quantity}")
    ResponseEntity<PurchaseResponseDto> buy(@PathVariable("username") String username, @PathVariable("productId") Long productId, @PathVariable("quantity") Integer quantity) {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            return new ResponseEntity(Collections.singletonMap(ERROR_RESPONSE_KEY, "Username does not exist"), HttpStatus.NOT_FOUND);
        }

        if (!"buyer".equalsIgnoreCase(user.getRole())) {
            return new ResponseEntity(Collections.singletonMap(ERROR_RESPONSE_KEY, "User is not authorized to perform a purchase"), HttpStatus.UNAUTHORIZED);
        }

        Product product = productService.findById(productId);
        if (product == null) {
            return new ResponseEntity(Collections.singletonMap(ERROR_RESPONSE_KEY, "Product does not exist"), HttpStatus.NOT_FOUND);
        }

        if (product.getAmountAvailable() < quantity) {
            return new ResponseEntity(Collections.singletonMap(ERROR_RESPONSE_KEY, "Product quantity is less than quantity demanded"), HttpStatus.NOT_FOUND);
        }

        if (product.getCost() > user.getDeposit() * quantity) {
            return new ResponseEntity(Collections.singletonMap(ERROR_RESPONSE_KEY, "User's deposit is not sufficient enough to buy the product"), HttpStatus.PRECONDITION_FAILED);
        }

        try {
            PurchaseResponseDto purchaseResponseDto = userService.buy(user, product, quantity);
            return ResponseEntity.status(HttpStatus.OK).body(purchaseResponseDto);
        } catch (Exception ex) {
            return new ResponseEntity(Collections.singletonMap(ERROR_RESPONSE_KEY, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/reset/{username}")
    ResponseEntity<UserDto> reset(@PathVariable("username") String username) {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            return new ResponseEntity(Collections.singletonMap(ERROR_RESPONSE_KEY, "Username does not exist"), HttpStatus.NOT_FOUND);
        }

        UserDto userDto = userMapper.entityToDto(userService.resetDeposit(user));
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }
}
