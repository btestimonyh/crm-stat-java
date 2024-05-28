package dev.guarmo.crmstat.repository;

import dev.guarmo.crmstat.model.proj.Project;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProjectRepository extends JpaRepository<Project, Long> {
//    Project findByLogin(String login);
}
