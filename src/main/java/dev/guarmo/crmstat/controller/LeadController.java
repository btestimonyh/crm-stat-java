package dev.guarmo.crmstat.controller;

import dev.guarmo.crmstat.model.lead.Lead;
import dev.guarmo.crmstat.model.lead.PostLeadDto;
import dev.guarmo.crmstat.service.LeadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/leads")
@CrossOrigin(origins = "*")
@Slf4j
public class LeadController {
    private final LeadService leadService;

    @PreAuthorize("hasRole('ROLE_BOT') || hasRole('ROLE_OWNER') || hasRole('ROLE_ADMIN')")
    @PostMapping
    public Lead addLead(@RequestBody PostLeadDto lead) {
        log.info("Saving lead: {}", lead);
        Lead saved = leadService.save(lead);
        log.info("Saved lead: {}", saved);
        return saved;
    }

    @PreAuthorize("hasRole('ROLE_BOT') || hasRole('ROLE_OWNER') || hasRole('ROLE_ADMIN')")
    @PutMapping("/{leadId}")
    public Lead updateLead(@RequestBody PostLeadDto lead, @PathVariable Long leadId) {
        log.info("Update by ID: {} LEAD: {}", leadId, lead);
        return leadService.update(lead, leadId);
    }

    @PreAuthorize("hasRole('ROLE_BOT') || hasRole('ROLE_OWNER') || hasRole('ROLE_ADMIN')")
    @PatchMapping("/{leadId}")
    public Lead patchLead(@PathVariable Long leadId, @RequestBody Map<String, Object> fields) {
        log.info("Patch by ID: {} FIELDS: {}", leadId, fields);
        return leadService.patchLead(leadId, fields);
    }

    @PreAuthorize("hasRole('ROLE_BOT') || hasRole('ROLE_OWNER') || hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<Lead> getAllLeads() {
        log.info("Get all leads");
        return leadService.findAll();
    }

    @PreAuthorize("hasRole('ROLE_BOT') || hasRole('ROLE_OWNER') || hasRole('ROLE_ADMIN')")
    @GetMapping("/{leadId}")
    public Lead getLead(@PathVariable Long leadId) {
        log.info("Getting lead by ID: {}", leadId);
        return leadService.findById(leadId);
    }

    @PreAuthorize("hasRole('ROLE_BOT') || hasRole('ROLE_OWNER') || hasRole('ROLE_ADMIN')")
    @GetMapping("/tg/{tgIdOfLead}")
    public Lead getLeadByTgId(@PathVariable String tgIdOfLead) {
        log.info("Getting lead by its TG ID: {}", tgIdOfLead);
        return leadService.findLeadByTgId(tgIdOfLead);
    }
}
