package com.example.PracticeCRUD.Dto.UserDto;

import java.time.LocalDateTime;

import com.example.PracticeCRUD.Enum.UserRole;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserAdminDto {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private UserRole role;
    private boolean active;
    private String profilePicture;
    private LocalDateTime createdAt;
}

