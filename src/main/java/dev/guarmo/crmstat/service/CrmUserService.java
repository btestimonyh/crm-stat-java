package dev.guarmo.crmstat.service;

import dev.guarmo.crmstat.mapper.GetCrmUserDtoMapper;
import dev.guarmo.crmstat.model.PermissionStatus;
import dev.guarmo.crmstat.model.Status;
import dev.guarmo.crmstat.model.user.CrmUser;
import dev.guarmo.crmstat.model.user.CreateCrmUserDto;
import dev.guarmo.crmstat.repository.CrmUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CrmUserService {
    private final CrmUserRepository crmUserRepository;
    private final GetCrmUserDtoMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public CrmUser createBuyer(CrmUser crmUser) {
        return crmUserRepository.save(crmUser);
    }

    public List<CrmUser> getAllBuyers() {
        return crmUserRepository.findAll();
    }

    public CrmUser getCrmUserById(String id) {
        return crmUserRepository.findById(id).orElseGet(() -> {
            log.error("Buyer is NOT FOUND with token: {} ", id);
            return null;}
        );
    }

//    public CrmUser saveBuyer(CrmUser crmUser) {
//        return crmUserRepository.save(crmUser);
//    }

    public CrmUser registerWithCertainRole(Status role, CreateCrmUserDto userDto) {
        CrmUser model = mapper.toModel(userDto);
        model.setPassword(passwordEncoder.encode(userDto.getPassword()));
        model.setRole(role);
        model.setPermissionStatus(PermissionStatus.ACTIVE);

        log.info("Saving crm user: {}", model);
        return crmUserRepository.save(model);
    }

    public CrmUser setPermissionStatusTo(PermissionStatus permissionStatus, String userId) {
        CrmUser crmUser = crmUserRepository.findById(userId).orElseThrow();
        crmUser.setPermissionStatus(permissionStatus);
        return crmUserRepository.save(crmUser);
    }

    public void deleteById(String crmUserId, Status actionAuthorStatus) {
        CrmUser byId = crmUserRepository.findById(crmUserId).orElseThrow();

        if (actionAuthorStatus == Status.ADMIN &&
                (
                        byId.getRole() == Status.ADMIN
                        || byId.getRole() == Status.OWNER
                        || byId.getRole() == Status.BOT)
        ) {
            // Do nothing when permissions are not high enough
            return;
        }
        crmUserRepository.deleteById(crmUserId);
    }
}
