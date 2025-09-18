package com.example.pib2.Users.model.dto.LoginUserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginDTO {

    private String username;
    private String password;
}
