package com.example.PracticeCRUD.service;

import com.example.PracticeCRUD.Dto.UserDto.UserAdminDto;
import com.example.PracticeCRUD.Dto.UserDto.UserCreateDto;
import com.example.PracticeCRUD.Dto.UserDto.UserProfileDto;
import com.example.PracticeCRUD.Dto.UserDto.UserUpdateDto;
import com.example.PracticeCRUD.Mapper.UserMapper;
import com.example.PracticeCRUD.entity.User;
import com.example.PracticeCRUD.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Get all users (Admin)
    public List<UserAdminDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toAdminDto)
                .collect(Collectors.toList());
    }

    // Get single user by id
    public UserProfileDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.toProfileDto(user);
    }

    // Create new user
    public UserProfileDto createUser(UserCreateDto dto) {
        // Optional: check if email exists
        userRepository.findByEmail(dto.getEmail())
                .ifPresent(u -> { throw new RuntimeException("Email already exists"); });

        User user = UserMapper.toEntity(dto);
        User saved = userRepository.save(user);
        return UserMapper.toProfileDto(saved);
    }

    // Update user
    public UserProfileDto updateUser(Long id, UserUpdateDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        UserMapper.updateEntity(user, dto);
        User updated = userRepository.save(user);
        return UserMapper.toProfileDto(updated);
    }

    // Delete user (soft delete could also be implemented)
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}
