package com.example.PracticeCRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PracticeCRUD.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

}
