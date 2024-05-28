package dev.guarmo.crmstat.security;

import dev.guarmo.crmstat.repository.CrmUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GuarmoUserDetailsService implements UserDetailsService {
    private final CrmUserRepository crmUserRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        log.info("Loading user by login: {}", login);
        log.info("User by login: {}", crmUserRepository.findByLogin(login).orElse(null));
        return crmUserRepository.findByLogin(login).orElseThrow(
                () -> new UsernameNotFoundException("Cant find user by login: " + login)
        );
    }
}
