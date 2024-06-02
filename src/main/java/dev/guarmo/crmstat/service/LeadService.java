package dev.guarmo.crmstat.service;

import dev.guarmo.crmstat.mapper.GetLeadDtoMapper;
import dev.guarmo.crmstat.mapper.PostLeadDtoMapper;
import dev.guarmo.crmstat.model.lead.GetLeadDto;
import dev.guarmo.crmstat.model.lead.Lead;
import dev.guarmo.crmstat.model.lead.PostLeadDto;
import dev.guarmo.crmstat.model.proj.Project;
import dev.guarmo.crmstat.repository.LeadRepository;
import dev.guarmo.crmstat.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LeadService {
    private final LeadRepository leadRepository;
    private final PostLeadDtoMapper mapper;
    private final DateService dateService;
    private final GetLeadDtoMapper getLeadDtoMapper;
    private final ProjectRepository projectRepository;

    public List<Lead> findAll() {
        return leadRepository.findAll();
    }

    public Lead findById(Long id) {
        return leadRepository.findById(id).orElseGet(() -> {
            log.error("Lead is NOT FOUND with token: {} ", id);
            return null;
        });
    }

    public Lead saveModel(Lead lead) {
        Optional<Lead> gotLeadById = leadRepository.findById(lead.getId());
        Project projectFromLead = projectRepository.findById(lead.getProject().getId()).orElseThrow();

        if (gotLeadById.isEmpty()) {
            if (lead.getIsSubscribed()) {
                projectFromLead.setSubs(projectFromLead.getSubs() + 1);
            } else {
                projectFromLead.setUnsubs(projectFromLead.getUnsubs() + 1);
            }
        } else {
            if (lead.getIsSubscribed() && !gotLeadById.get().getIsSubscribed()) {
                projectFromLead.setSubs(projectFromLead.getSubs() + 1);
            } else if (!lead.getIsSubscribed() && gotLeadById.get().getIsSubscribed()) {
                projectFromLead.setUnsubs(projectFromLead.getUnsubs() + 1);
            }
        }
        Project savedProj = projectRepository.save(projectFromLead);
        Lead savedLead = leadRepository.save(lead);
        log.info("Lead saved: {}", savedLead);
        log.info("Project subs count updated: {}", savedProj);
        return savedLead;
    }

    public Lead save(PostLeadDto lead) {
        Lead model = mapper.toModel(lead);
        return saveModel(model);
    }

    public Lead update(PostLeadDto updatedLead, Long updatedLeadId) {
        Lead model = mapper.toModel(updatedLead);
        model.setId(updatedLeadId);
        return saveModel(model);
    }

    public List<GetLeadDto> findAllByProjectId(Long id, Integer gmtShift) {
        List<Lead> leadsByProjectId = leadRepository.getLeadsByProject_Id(id);
        log.info("Lead list is {}", leadsByProjectId);

        leadsByProjectId.forEach(lead -> lead.setRegDate(lead.getRegDate().plusHours(gmtShift)));
        List<GetLeadDto> getLeadDtoStream = leadsByProjectId.stream().map(getLeadDtoMapper::toDto).toList();

        log.info("Get Lead Dto list with synchronized date {}", getLeadDtoStream);
        return getLeadDtoStream;
    }

    public Lead findLeadByTgId(String tgIdOfLead) {
        return leadRepository.findFirstByTgid(tgIdOfLead);
    }

    public Lead patchLead(Long id, Map<String, Object> fields) {
        Lead existingLead = leadRepository.findById(id).orElseThrow();
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Lead.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingLead, value);
        });

        Lead saved = saveModel(existingLead);
        log.info("Edited with PATCH: {}", saved);
        return saved;
    }
}
