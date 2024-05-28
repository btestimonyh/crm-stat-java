package dev.guarmo.crmstat.security;

import dev.guarmo.crmstat.mapper.LoginUserDtoMapper;
import dev.guarmo.crmstat.mapper.LoginUserResponseDtoMapper;
import dev.guarmo.crmstat.model.user.CrmUser;
import dev.guarmo.crmstat.model.user.LoginUserDto;
import dev.guarmo.crmstat.model.user.LoginUserResponseDto;
import dev.guarmo.crmstat.repository.CrmUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final LoginUserResponseDtoMapper responseMapper;
    private final CrmUserRepository crmUserRepository;

    public LoginUserResponseDto auth(LoginUserDto userDto) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDto.getLogin(), userDto.getPassword()
                )
        );
        String token = jwtUtil.generateToken(authentication.getName());
        CrmUser model = crmUserRepository.findByLogin(authentication.getName()).orElseThrow();

        log.info("Log in this user: {}", model);
        LoginUserResponseDto dto = responseMapper.toDto(model);
        dto.setToken(token);
        dto.setPassword("password is hidden");
        return dto;
    }
}
