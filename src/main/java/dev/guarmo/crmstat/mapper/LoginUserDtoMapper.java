package dev.guarmo.crmstat.mapper;

import dev.guarmo.crmstat.config.MapperConfig;
import dev.guarmo.crmstat.model.lead.Lead;
import dev.guarmo.crmstat.model.lead.PostLeadDto;
import dev.guarmo.crmstat.model.user.CrmUser;
import dev.guarmo.crmstat.model.user.LoginUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface LoginUserDtoMapper {
    LoginUserDto toDto(CrmUser crmUser);

    CrmUser toModel(LoginUserDto loginUserDto);
}
