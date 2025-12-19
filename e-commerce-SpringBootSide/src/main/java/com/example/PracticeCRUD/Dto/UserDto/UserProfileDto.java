package com.example.PracticeCRUD.Dto.UserDto;



import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserProfileDto {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String profilePicture;
    private boolean active;
}

