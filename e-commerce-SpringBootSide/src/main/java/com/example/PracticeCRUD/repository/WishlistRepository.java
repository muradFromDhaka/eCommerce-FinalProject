package com.example.PracticeCRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PracticeCRUD.entity.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Long>{

}
