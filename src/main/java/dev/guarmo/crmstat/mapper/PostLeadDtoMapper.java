package dev.guarmo.crmstat.mapper;

import dev.guarmo.crmstat.config.MapperConfig;
import dev.guarmo.crmstat.model.lead.Lead;
import dev.guarmo.crmstat.model.lead.PostLeadDto;
import dev.guarmo.crmstat.model.proj.Project;
import dev.guarmo.crmstat.service.DateService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class, uses = {DateService.class})
public interface PostLeadDtoMapper {
    //    @Mapping(target = "imageId", source = "image.token")
    @Mapping(target = "projectId", source = "project.id")
    PostLeadDto toDto(Lead lead);

    //    @Mapping(target = "image", source = "imageId", qualifiedByName = "imageFromId")
    @Mapping(target = "project", source = "projectId", qualifiedByName = "projectFromId")
    @Mapping(target = "regDate", source = "regDate", qualifiedByName = "mapFromStringToLocalDate")
//    @Mapping(target = "regDate", source = "regDate", ignore = true)
    Lead toModel(PostLeadDto postLeadDto);

    @Named("projectFromId")
    default Project projectFromId(Long id) {
        if (id != null) {
            return new Project(id);
        }
        return null;
    }
}
