package com.example.PracticeCRUD.Mapper;


import com.example.PracticeCRUD.Dto.UserDto.UserAdminDto;
import com.example.PracticeCRUD.Dto.UserDto.UserBaseDto;
import com.example.PracticeCRUD.Dto.UserDto.UserCreateDto;
import com.example.PracticeCRUD.Dto.UserDto.UserProfileDto;
import com.example.PracticeCRUD.Dto.UserDto.UserUpdateDto;
import com.example.PracticeCRUD.entity.User;


public class UserMapper {

    // Entity → Base DTO
    public static UserBaseDto toBaseDto(User user) {
        if (user == null) return null;

        UserBaseDto dto = new UserBaseDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setProfilePicture(user.getProfilePicture());
        return dto;
    }

    // Entity → Profile DTO
    public static UserProfileDto toProfileDto(User user) {
        if (user == null) return null;

        UserProfileDto dto = new UserProfileDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setProfilePicture(user.getProfilePicture());
        dto.setPhone(user.getPhone());
        dto.setAddress(user.getAddress());
        dto.setActive(user.isActive());
        return dto;
    }

    // Entity → Admin DTO
    public static UserAdminDto toAdminDto(User user) {
        if (user == null) return null;

        UserAdminDto dto = new UserAdminDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setProfilePicture(user.getProfilePicture());
        dto.setPhone(user.getPhone());
        dto.setRole(user.getRole());
        dto.setActive(user.isActive());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }

    // Create DTO → Entity
    public static User toEntity(UserCreateDto dto) {
        if (dto == null) return null;

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhone(dto.getPhone());
        user.setActive(true);
        return user;
    }

    // Update DTO → Entity (partial update)
    public static void updateEntity(User user, UserUpdateDto dto) {
        if (user == null || dto == null) return;

        if (dto.getName() != null) user.setName(dto.getName());
        if (dto.getPhone() != null) user.setPhone(dto.getPhone());
        if (dto.getAddress() != null) user.setAddress(dto.getAddress());
        if (dto.getProfilePicture() != null) user.setProfilePicture(dto.getProfilePicture());

        
    }
}
