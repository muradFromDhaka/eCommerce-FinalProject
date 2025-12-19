package com.example.PracticeCRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PracticeCRUD.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
