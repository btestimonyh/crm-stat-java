package dev.guarmo.crmstat.service;

import dev.guarmo.crmstat.mapper.GetProjectDtoMapper;
import dev.guarmo.crmstat.model.lead.GetLeadDto;
import dev.guarmo.crmstat.model.lead.Lead;
import dev.guarmo.crmstat.model.proj.GetProjectDto;
import dev.guarmo.crmstat.model.proj.Project;
import dev.guarmo.crmstat.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectAfterInitializationService {
    private final LeadService leadService;
    private final ProjectRepository projectRepository;
    private final GetProjectDtoMapper getMapper;

    public GetProjectDto finishInitializingGetProjectDto(Long id, Integer gmtShift) {
        GetProjectDto dto = projectRepository.findById(id).map(getMapper::toDto).orElseThrow();
        List<GetLeadDto> leads = leadService.findAllByProjectId(id, gmtShift);
        dto.setLeads(leads);
        return dto;

    }

    public List<GetProjectDto> findAllGetProjectDto(Integer gmtShift) {
        List<Project> models = projectRepository.findAll();
        List<GetProjectDto> dtos = models.stream().map(getMapper::toDto).toList();
        dtos.forEach(dto -> {
            Long dtoId = dto.getId();
            List<GetLeadDto> leads = leadService.findAllByProjectId(dtoId, gmtShift);
            // Assuming setLeads() is a method to set the leads to the DTO
            dto.setLeads(leads);
        });
        log.info("DTOS: ", dtos);
        return dtos;

    }
}
