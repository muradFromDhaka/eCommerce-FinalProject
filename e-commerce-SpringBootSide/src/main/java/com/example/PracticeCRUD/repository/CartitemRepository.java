package com.example.PracticeCRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PracticeCRUD.entity.CartItem;

@Repository
public interface CartitemRepository extends JpaRepository<CartItem, Long>{

}
