package dev.guarmo.crmstat.mapper;

import dev.guarmo.crmstat.config.MapperConfig;
import dev.guarmo.crmstat.model.user.CrmUser;
import dev.guarmo.crmstat.model.proj.PostProjectDto;
import dev.guarmo.crmstat.model.proj.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface PostProjectDtoMapper {
    //    @Mapping(target = "imageId", source = "image.token")
    @Mapping(target = "buyerId", source = "crmUser.id")
    PostProjectDto toDto(Project project);

    //    @Mapping(target = "image", source = "imageId", qualifiedByName = "imageFromId")
    @Mapping(target = "crmUser", source = "buyerId", qualifiedByName = "crmUserFromId")
    Project toModel(PostProjectDto postProjectDto);

    @Named("crmUserFromId")
    default CrmUser crmUserFromId(String id) {
        if (id != null) {
            return new CrmUser(id);
        }
        return null;
    }
}
