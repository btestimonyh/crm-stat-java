package dev.guarmo.crmstat.service;

import dev.guarmo.crmstat.mapper.PostLeadDtoMapper;
import dev.guarmo.crmstat.model.lead.Lead;
import dev.guarmo.crmstat.model.lead.PostLeadDto;
import dev.guarmo.crmstat.repository.LeadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.el.util.ReflectionUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class LeadService {
    private final LeadRepository leadRepository;
    private final PostLeadDtoMapper mapper;
    private final GetClassFieldsService getClassFieldsService;

    public List<Lead> findAll() {
        return leadRepository.findAll();
    }

    public Lead findById(Long id) {
        return leadRepository.findById(id).orElseGet(() -> {
            log.error("Lead is NOT FOUND with token: {} ", id);
            return null;}
        );
    }

    public Lead save(PostLeadDto lead) {
        Lead model = mapper.toModel(lead);
        return leadRepository.save(model);
    }

    public Lead update(PostLeadDto updatedLead, Long updatedLeadId) {
        Lead model = mapper.toModel(updatedLead);
        model.setId(updatedLeadId);
        return leadRepository.save(model);
    }

    public List<Lead> findAllByProjectId(Long id) {
        List<Lead> leadsByProjectId = leadRepository.getLeadsByProject_Id(id);
        log.info("Lead list is {}", leadsByProjectId);
        leadsByProjectId.forEach(lead -> {
            String[] dates = lead.getRegDate().split(" ")[0].split("-");
            String finalData = dates[2] + "." + dates[1] + "." + dates[0];
            lead.setRegDate(finalData);
        });
        log.info("Lead list with UPD date {}", leadsByProjectId);
        return leadsByProjectId;
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

        Lead saved = leadRepository.save(existingLead);
        log.info("Edited with PATCH: {}", saved);
        return saved;
    }
}
