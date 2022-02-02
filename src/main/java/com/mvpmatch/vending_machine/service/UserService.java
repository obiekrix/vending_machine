package com.mvpmatch.vending_machine.service;

import com.mvpmatch.vending_machine.dto.PurchaseResponseDto;
import com.mvpmatch.vending_machine.entity.Product;
import com.mvpmatch.vending_machine.entity.User;
import com.mvpmatch.vending_machine.repository.ProductRepository;
import com.mvpmatch.vending_machine.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public UserService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public User create(User user) {
        String hashedPassword = DigestUtils.sha256Hex(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    public User deposit(User user, Integer amount) {
        user.setDeposit(user.getDeposit() + amount);
        return userRepository.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public PurchaseResponseDto buy(User user, Product product, Integer quantity) {
        Long totalSpent = product.getCost() * quantity;

        user.setDeposit(user.getDeposit() - totalSpent);
        product.setAmountAvailable(product.getAmountAvailable() - quantity);

        userRepository.save(user);
        productRepository.save(product);

        PurchaseResponseDto purchaseResponseDto = new PurchaseResponseDto();
        purchaseResponseDto.setTotalSpent(totalSpent);
        purchaseResponseDto.setProductPurchased(product.getName());
        purchaseResponseDto.setRemainingDeposit(user.getDeposit());

        return purchaseResponseDto;
    }

    public User resetDeposit(User user){
        user.setDeposit(0L);
        userRepository.save(user);

        return user;
    }
}
