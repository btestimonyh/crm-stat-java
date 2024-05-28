package dev.guarmo.crmstat.mapper;

import dev.guarmo.crmstat.config.MapperConfig;
import dev.guarmo.crmstat.model.user.CrmUser;
import dev.guarmo.crmstat.model.user.LoginUserDto;
import dev.guarmo.crmstat.model.user.LoginUserResponseDto;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface LoginUserResponseDtoMapper {
    LoginUserResponseDto toDto(CrmUser crmUser);

    CrmUser toModel(LoginUserResponseDto loginUserDto);
}
