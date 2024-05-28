package dev.guarmo.crmstat.mapper;

import dev.guarmo.crmstat.config.MapperConfig;
import dev.guarmo.crmstat.model.proj.Project;
import dev.guarmo.crmstat.model.user.CrmUser;
import dev.guarmo.crmstat.model.user.CreateCrmUserDto;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(config = MapperConfig.class)
public interface GetCrmUserDtoMapper {
//    @Mapping(target = "projectIds", ignore = true)
    CreateCrmUserDto toDto(CrmUser user);

//    @AfterMapping
//    default void initIdsFromResponses(@MappingTarget CreateCrmUserDto dto,
//                                      CrmUser model) {
//        List<Long> responseIds = model.getProjects()
//                .stream()
//                .map(Project::getId)
//                .toList();
//        dto.setProjectIds(responseIds);
//    }


//    @Mapping(target = "projects", ignore = true)
    CrmUser toModel(CreateCrmUserDto crmUserDto);

//    @AfterMapping
//    default void initSubjectsFromIds(@MappingTarget CrmUser model,
//                                     CreateCrmUserDto createDto) {
//        if (createDto.getProjectIds() == null) {
//            createDto.setProjectIds(new ArrayList<>());
//        }
//
//        List<Project> dummies = createDto.getProjectIds()
//                .stream()
//                .map(Project::new)
//                .toList();
//        model.setProjects(dummies);
//    }
}
