package com.example.PracticeCRUD.Dto.UserDto;



import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserUpdateDto {

    private String name;
    private String phone;
    private String address;
    private String profilePicture;
}

