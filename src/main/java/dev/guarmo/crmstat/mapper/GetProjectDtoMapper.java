package dev.guarmo.crmstat.mapper;

import dev.guarmo.crmstat.config.MapperConfig;
import dev.guarmo.crmstat.model.proj.GetProjectDto;
import dev.guarmo.crmstat.model.proj.PostProjectDto;
import dev.guarmo.crmstat.model.proj.Project;
import dev.guarmo.crmstat.model.user.CrmUser;
import dev.guarmo.crmstat.model.user.LoginUserResponseDto;
import dev.guarmo.crmstat.service.ProjectService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = MapperConfig.class)
public interface GetProjectDtoMapper {
    //    @Mapping(target = "imageId", source = "image.token")
    @Mapping(target = "buyerId", source = "crmUser.id")
    GetProjectDto toDto(Project project);
//    @AfterMapping
//    default void initIdsFromResponses(@MappingTarget GetProjectDto dto, Project model) {
//
//    }

    //    @Mapping(target = "image", source = "imageId", qualifiedByName = "imageFromId")
    @Mapping(target = "crmUser", source = "buyerId", qualifiedByName = "crmUserFromId")
    Project toModel(GetProjectDto dto);

    @Named("crmUserFromId")
    default CrmUser crmUserFromId(String id) {
        if (id != null) {
            return new CrmUser(id);
        }
        return null;
    }
}
