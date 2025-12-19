package com.example.PracticeCRUD.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.PracticeCRUD.Dto.CartDto.CartDto;
import com.example.PracticeCRUD.entity.Cart;
import com.example.PracticeCRUD.service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CartController {

    private final CartService cartService;

    // -----------------------------
    // GET ALL CARTS
    // -----------------------------
    @GetMapping
    public ResponseEntity<List<CartDto>> getAllCarts() {
        return ResponseEntity.ok(cartService.getAllCarts());
    }

    // -----------------------------
    // GET CART BY ID
    // -----------------------------
    @GetMapping("/{id}")
    public ResponseEntity<CartDto> getCartById(@PathVariable Long id) {
        CartDto cart = cartService.getCartById(id);

        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cart);
    }

    // -----------------------------
    // CREATE CART
    // -----------------------------
    @PostMapping
    public ResponseEntity<CartDto> createCart(@RequestBody Cart cart) {
        CartDto savedCart = cartService.saveCart(cart);
        return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
    }

    // -----------------------------
    // UPDATE CART
    // -----------------------------
    @PutMapping("/{id}")
    public ResponseEntity<CartDto> updateCart(
            @PathVariable Long id,
            @RequestBody Cart cart) {

        cart.setId(id); // important
        CartDto updatedCart = cartService.saveCart(cart);
        return ResponseEntity.ok(updatedCart);
    }

    // -----------------------------
    // DELETE CART
    // -----------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }
}
