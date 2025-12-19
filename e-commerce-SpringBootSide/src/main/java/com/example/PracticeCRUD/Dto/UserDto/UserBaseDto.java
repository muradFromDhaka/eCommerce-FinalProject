package com.example.PracticeCRUD.Dto.UserDto;



import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserBaseDto {

    private Long id;
    private String name;
    private String email;
    private String profilePicture;
}

