package com.mvpmatch.vending_machine.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "_user")
public class User {
    @Id
    @Column(name = "_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "_username")
    @NotBlank(message = "username must not be blank")
    private String username;

    @Column(name = "_deposit")
    @NotNull(message = "Please set initial deposit value")
    private Long deposit;

    @Column(name = "_password")
    @NotBlank(message = "Enter a password")
    private String password;

    @Column(name = "_role")
    @NotBlank(message = "Enter a role (i.e. buyer or seller)")
    private String role;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL)
    private List<Product> products;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getDeposit() {
        return deposit;
    }

    public void setDeposit(Long deposit) {
        this.deposit = deposit;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
