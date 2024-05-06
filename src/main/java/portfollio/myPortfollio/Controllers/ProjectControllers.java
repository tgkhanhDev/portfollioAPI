package portfollio.myPortfollio.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portfollio.myPortfollio.Entities.Project;
import portfollio.myPortfollio.Services.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectControllers {

    ProjectService projectService;

    @Autowired
    public ProjectControllers(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("")
    public List<Project> getAllProjects(){
        return projectService.findAll();
    }

    public Project getById(){

    }
}
