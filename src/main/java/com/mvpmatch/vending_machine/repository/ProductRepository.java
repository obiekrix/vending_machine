package com.mvpmatch.vending_machine.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mvpmatch.vending_machine.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>, JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    Page<Product> findAll(Pageable pageable);
}