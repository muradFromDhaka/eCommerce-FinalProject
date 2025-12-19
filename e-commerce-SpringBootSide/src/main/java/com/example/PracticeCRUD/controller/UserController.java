package com.example.PracticeCRUD.controller;

import com.example.PracticeCRUD.Dto.UserDto.UserAdminDto;
import com.example.PracticeCRUD.Dto.UserDto.UserCreateDto;
import com.example.PracticeCRUD.Dto.UserDto.UserProfileDto;
import com.example.PracticeCRUD.Dto.UserDto.UserUpdateDto;
import com.example.PracticeCRUD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    // GET all users (Admin)
    @GetMapping
    public ResponseEntity<List<UserAdminDto>> getAllUsers() {
        List<UserAdminDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // GET single user
    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDto> getUser(@PathVariable Long id) {
        UserProfileDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // POST create new user
    @PostMapping
    public ResponseEntity<UserProfileDto> createUser(@RequestBody UserCreateDto dto) {
        UserProfileDto user = userService.createUser(dto);
        return ResponseEntity.ok(user);
    }

    // PUT update user
    @PutMapping("/{id}")
    public ResponseEntity<UserProfileDto> updateUser(@PathVariable Long id,
                                                     @RequestBody UserUpdateDto dto) {
        UserProfileDto user = userService.updateUser(id, dto);
        return ResponseEntity.ok(user);
    }

    // DELETE user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
