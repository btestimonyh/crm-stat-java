package dev.guarmo.crmstat.model.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserDto {
    private String login;
    private String password;
}
