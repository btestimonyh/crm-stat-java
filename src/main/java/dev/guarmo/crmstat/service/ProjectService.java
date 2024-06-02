package dev.guarmo.crmstat.service;

import dev.guarmo.crmstat.mapper.GetProjectDtoMapper;
import dev.guarmo.crmstat.mapper.PostProjectDtoMapper;
import dev.guarmo.crmstat.model.lead.Lead;
import dev.guarmo.crmstat.model.proj.GetProjectDto;
import dev.guarmo.crmstat.model.proj.PostProjectDto;
import dev.guarmo.crmstat.model.proj.Project;
import dev.guarmo.crmstat.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final PostProjectDtoMapper mapper;
    private final GetProjectDtoMapper getMapper;
    private final ProjectAfterInitializationService projectAfterInitializationService;

    public GetProjectDto findById(Long id, Integer gmtShift) {
       return projectAfterInitializationService.finishInitializingGetProjectDto(id, gmtShift);
    }

    public Project saveProject(PostProjectDto projectDto) {
        Project model = mapper.toModel(projectDto);
        return projectRepository.save(model);
    }

    public List<GetProjectDto> findAll(Integer gmtShift) {
        return projectAfterInitializationService.findAllGetProjectDto(gmtShift);
    }

    public Project patchProject(Long id, Map<String, Object> fields) {
        Project existingProject = projectRepository.findById(id).orElseThrow();
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Project.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingProject, value);
        });

        Project saved = projectRepository.save(existingProject);
        log.info("Edited project with PATCH: {}", saved);
        return saved;
    }
}
