package dev.guarmo.crmstat.controller;

import dev.guarmo.crmstat.model.PermissionStatus;
import dev.guarmo.crmstat.model.Status;
import dev.guarmo.crmstat.model.proj.Project;
import dev.guarmo.crmstat.model.user.CreateCrmUserDto;
import dev.guarmo.crmstat.model.user.CrmUser;
import dev.guarmo.crmstat.service.CrmUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Permission;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
//@CrossOrigin(origins = "allowedOriginPatterns", allowCredentials = "true", allowedHeaders = "Authorization", methods =
//        {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@Slf4j
@CrossOrigin(origins = "*")
public class UserController {
    private final CrmUserService crmUserService;
    private final PasswordEncoder passwordEncoder;
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_OWNER')")
    @GetMapping
    public List<CrmUser> getAllBuyers() {
        log.info("Getting all buyers");
        return crmUserService.getAllBuyers();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_OWNER')")
    @PostMapping("/register/buyer")
    public CrmUser createBuyer(@RequestBody CreateCrmUserDto crmUser) {
        return crmUserService.registerWithCertainRole(Status.BUYER, crmUser);
    }

    @PreAuthorize("hasRole('ROLE_OWNER')")
    @PostMapping("/register/admin")
    public CrmUser createAdmin(@RequestBody CreateCrmUserDto crmUser) {
        return crmUserService.registerWithCertainRole(Status.ADMIN, crmUser);
    }

    @PreAuthorize("hasRole('ROLE_OWNER') || hasRole('ROLE_ADMIN')")
    @PutMapping("/activate/{userId}")
    public CrmUser setActiveStatus(@PathVariable String userId) {
        return crmUserService.setPermissionStatusTo(PermissionStatus.ACTIVE, userId);
    }

    @PreAuthorize("hasRole('ROLE_OWNER') || hasRole('ROLE_ADMIN')")
    @PutMapping("/deactivate/{userId}")
    public CrmUser setInactiveStatus(@PathVariable String userId) {
        return crmUserService.setPermissionStatusTo(PermissionStatus.INACTIVE, userId);
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_OWNER') || hasRole('ROLE_BUYER')")
    @GetMapping("/{crmUserId}")
    public CrmUser getUserById(@PathVariable String crmUserId) {
        log.info("Getting user by id: {}", crmUserId);
        return crmUserService.getCrmUserById(crmUserId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_OWNER')")
    @DeleteMapping("/{crmUserId}")
    public void deleteBuyerById(@PathVariable String crmUserId, Authentication authentication) {
        CrmUser principal = (CrmUser) authentication.getPrincipal();
        crmUserService.deleteById(crmUserId, principal.getRole());
    }
}
