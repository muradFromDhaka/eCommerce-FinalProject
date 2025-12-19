package com.example.PracticeCRUD.Dto.UserDto;



import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserCreateDto {

    private String name;
    private String email;
    private String password;
    private String phone;
}

