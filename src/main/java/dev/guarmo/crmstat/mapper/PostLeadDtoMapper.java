package dev.guarmo.crmstat.mapper;

import dev.guarmo.crmstat.config.MapperConfig;
import dev.guarmo.crmstat.model.lead.Lead;
import dev.guarmo.crmstat.model.lead.PostLeadDto;
import dev.guarmo.crmstat.model.proj.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface PostLeadDtoMapper {
    //    @Mapping(target = "imageId", source = "image.token")
    @Mapping(target = "projectId", source = "project.id")
    PostLeadDto toDto(Lead lead);

    //    @Mapping(target = "image", source = "imageId", qualifiedByName = "imageFromId")
    @Mapping(target = "project", source = "projectId", qualifiedByName = "projectFromId")
    Lead toModel(PostLeadDto postLeadDto);

    @Named("projectFromId")
    default Project projectFromId(Long id) {
        if (id != null) {
            return new Project(id);
        }
        return null;
    }
}
