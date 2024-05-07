package portfollio.myPortfollio.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import portfollio.myPortfollio.Entities.Project;
import portfollio.myPortfollio.Services.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectControllers implements REST<Project> {

    ProjectService projectService;

    @Autowired
    public ProjectControllers(ProjectService projectService) {
        this.projectService = projectService;
    }


    @GetMapping("")
    @Override
    public List<Project> getAllItems() {
        return projectService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public Project getById(@PathVariable int id) {
        Project prj = projectService.findById(id);
        if(prj == null){
            throw new RuntimeException("Employee id not found - "+id);
        }
        return prj;
    }

    @Override
    @PostMapping
    public Project addItem(@RequestBody Project item) {
        return projectService.add(item);
    }
    @Override
    @PutMapping
    public Project updateItem(@RequestBody Project item) {
        Project prj = projectService.findById(item.getProjectID());
        if(prj == null){
            throw new RuntimeException("Employee not found - "+ item.toString());
        }
        return projectService.update(item);
    }
    @Override
    public String deleteItem(int id) {
        return "";
    }
}
