package dev.guarmo.crmstat.controller;

import dev.guarmo.crmstat.model.Status;
import dev.guarmo.crmstat.model.user.CreateCrmUserDto;
import dev.guarmo.crmstat.model.user.CrmUser;
import dev.guarmo.crmstat.model.user.LoginUserDto;
import dev.guarmo.crmstat.model.user.LoginUserResponseDto;
import dev.guarmo.crmstat.security.AuthService;
import dev.guarmo.crmstat.service.CrmUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@Slf4j
public class AuthController {
    private final AuthService authService;
    private final CrmUserService crmUserService;

    @PostMapping
    public CrmUser registerOwner(@RequestBody CreateCrmUserDto crmUser) {
        log.info("Saving buyer: {}", crmUser);
        CrmUser crmUserSaved = crmUserService.registerWithCertainRole(Status.OWNER, crmUser);
        log.info("Saved buyer: {}", crmUserSaved);
        return crmUserSaved;
    }


    @PostMapping("/login")
    public LoginUserResponseDto loginCrmUser(@RequestBody LoginUserDto crmUser) {
        return authService.auth(crmUser);
    }

}
