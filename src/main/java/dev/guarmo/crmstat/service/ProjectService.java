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

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final PostProjectDtoMapper mapper;
    private final GetProjectDtoMapper getMapper;
    private final ProjectAfterInitializationService projectAfterInitializationService;

    public GetProjectDto findById(Long id) {
       return projectAfterInitializationService.finishInitializingGetProjectDto(id);
    }

    public Project saveProject(PostProjectDto projectDto) {
        Project model = mapper.toModel(projectDto);
        return projectRepository.save(model);
    }

    public List<GetProjectDto> findAll() {
        return projectAfterInitializationService.findAllGetProjectDto();
    }
}
