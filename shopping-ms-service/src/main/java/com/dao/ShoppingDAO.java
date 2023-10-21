package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.CartEntity;

@Repository
public interface ShoppingDAO extends JpaRepository<CartEntity, Long>{

}
