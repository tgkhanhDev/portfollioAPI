package portfollio.myPortfollio.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Fetched all Project in db Success")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Fetch all Project",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Not Available",
                    content = @Content)

    })
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
    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable int id) {
        Project prj = projectService.findById(id);
//        projectService.deleteById(id);
        if(prj == null){
            throw new RuntimeException("Employee not found.");
        }else{
            projectService.deleteById(id);
            return "Delete Success";
        }
    }
}
