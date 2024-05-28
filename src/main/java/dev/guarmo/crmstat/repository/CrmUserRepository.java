package dev.guarmo.crmstat.repository;

import dev.guarmo.crmstat.model.user.CrmUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CrmUserRepository extends JpaRepository<CrmUser, String> {
    Optional<CrmUser> findByLogin(String email);
}
