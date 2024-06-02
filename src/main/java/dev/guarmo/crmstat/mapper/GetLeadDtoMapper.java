package dev.guarmo.crmstat.mapper;

import dev.guarmo.crmstat.config.MapperConfig;
import dev.guarmo.crmstat.model.lead.GetLeadDto;
import dev.guarmo.crmstat.model.lead.Lead;
import dev.guarmo.crmstat.model.proj.GetProjectDto;
import dev.guarmo.crmstat.model.proj.Project;
import dev.guarmo.crmstat.service.DateService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Mapper(config = MapperConfig.class, uses = {DateService.class})
public interface GetLeadDtoMapper {
    @Mapping(target = "projectId", source = "project.id")
    @Mapping(target = "regDate", source = "regDate", qualifiedByName = "mapFromLocalDateToString")
    GetLeadDto toDto(Lead lead);

//    @Mapper(config = MapperConfig.class, componentModel = "spring")
//    @AfterMapping
//    default void mapProject(@MappingTarget GetLeadDto dto, Lead model, @Autowired DateService dateService) {
//    }

    @Mapping(target = "project", source = "projectId", qualifiedByName = "projectFromId")
    Lead toModel(GetLeadDto getLeadDto);

    @Named("projectFromId")
    default Project projectFromId(Long id) {
        if (id != null) {
            return new Project(id);
        }
        return null;
    }
}
