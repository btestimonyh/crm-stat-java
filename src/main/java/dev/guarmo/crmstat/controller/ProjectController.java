package dev.guarmo.crmstat.controller;

import dev.guarmo.crmstat.model.lead.Lead;
import dev.guarmo.crmstat.model.proj.GetProjectDto;
import dev.guarmo.crmstat.model.user.CrmUser;
import dev.guarmo.crmstat.model.proj.PostProjectDto;
import dev.guarmo.crmstat.model.proj.Project;
import dev.guarmo.crmstat.service.CrmUserService;
import dev.guarmo.crmstat.service.LeadService;
import dev.guarmo.crmstat.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
@CrossOrigin(origins = "*")
@Slf4j
public class ProjectController {
    private final ProjectService projectService;

    @PreAuthorize("hasRole('ROLE_BUYER') || hasRole('ROLE_OWNER') || hasRole('ROLE_ADMIN')")
    @PostMapping
    public void createProject(@RequestBody PostProjectDto project) {
        log.info("Creating new project: {}", project);
        Project saved = projectService.saveProject(project);
        log.info("Saved project: {}", saved);
    }

//    @PreAuthorize("hasRole('ROLE_BUYER ') || hasRole('ROLE_OWNER') || hasRole('ROLE_ADMIN')")
//    @GetMapping("/leads/{projectId}")
//    public List<Lead> getAllLeadsOfProjects(@PathVariable Long projectId) {
//        return leadService.findAllByProjectId(projectId);
//    }

    @PreAuthorize("hasRole('ROLE_BUYER') || hasRole('ROLE_OWNER') || hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<GetProjectDto> getAllProjects() {
        return projectService.findAll();
    }

    @PreAuthorize("hasRole('ROLE_BUYER') || hasRole('ROLE_OWNER') || hasRole('ROLE_ADMIN')")
    @GetMapping("/{projectId}")
    public GetProjectDto getProjectById(@PathVariable Long projectId) {
        return projectService.findById(projectId);
    }
}
