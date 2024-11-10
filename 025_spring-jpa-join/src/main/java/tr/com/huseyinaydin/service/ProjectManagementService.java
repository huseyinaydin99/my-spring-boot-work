package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.bo.ProjectEngineerResponseBO;
import tr.com.huseyinaydin.entity.Engineer;
import tr.com.huseyinaydin.entity.Project;
import tr.com.huseyinaydin.repository.EngineerRepository;
import tr.com.huseyinaydin.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot
 * @since 1994
 */

@Service
public class ProjectManagementService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EngineerRepository engineerRepository;

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    public List<Engineer> getEngineers() {
        return engineerRepository.findAll();
    }

    public String deleteProject(int projectId) {
        projectRepository.deleteById(projectId);
        return projectId + " Id'li proje silindi.";
    }

    public List<ProjectEngineerResponseBO> getProjectSpecificInfoSQL() {
        return projectRepository.getProjectSpecificInfoWithSQL();
    }

    public List<ProjectEngineerResponseBO> getProjectSpecificInfoJPQL() {
        return projectRepository.getProjectSpecificInfoWithJPQL();
    }
}