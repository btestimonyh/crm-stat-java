package dev.guarmo.crmstat.repository;

import dev.guarmo.crmstat.model.lead.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeadRepository extends JpaRepository<Lead, Long> {
    List<Lead> getLeadsByProject_Id(Long projectId);

    Lead findFirstByTgid(String tgIdOfLead);

    List<Lead> findAllByProject_IdAndIsSubscribed(Long projectId, Boolean isSubscribed);
}
