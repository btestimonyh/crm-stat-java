package dev.guarmo.crmstat.model.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserResponseDto {
    private String id;
    private String token;
    private String login;
    private String password;

    public LoginUserResponseDto(String token) {
        this.token = token;
    }
}
