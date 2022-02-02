package com.mvpmatch.vending_machine.repository;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mvpmatch.vending_machine.entity.User;

import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<User,Long>,JpaRepository<User,Long>,PagingAndSortingRepository<User,Long>,JpaSpecificationExecutor<User>{

    User findUserByUsername(@Param("username") String username);
}