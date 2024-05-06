package portfollio.myPortfollio.Entities;

import jakarta.persistence.*;

@Entity(name = "project_functions")
@Table(name = "project_functions")
public class ProjectFunc {
    @Id
    @Column(name = "functionID")
    private int functionID;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name="projectID")
    private Project project;

    public ProjectFunc(int functionID, String description, Project project) {
        this.functionID = functionID;
        this.description = description;
        this.project = project;
    }

    public ProjectFunc() {
    }

    public int getFunctionID() {
        return functionID;
    }

    public void setFunctionID(int functionID) {
        this.functionID = functionID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
