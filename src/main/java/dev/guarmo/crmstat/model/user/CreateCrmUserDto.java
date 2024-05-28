package dev.guarmo.crmstat.model.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateCrmUserDto {
    private String name;
    private String login;
    private String telegram;
    private String password;
    private String tag;
    private String registrationDate;
//    private List<Long> projectIds;
}
