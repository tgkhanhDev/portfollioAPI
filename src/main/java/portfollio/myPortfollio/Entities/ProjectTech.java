package portfollio.myPortfollio.Entities;

import jakarta.persistence.*;

@Entity(name="project_tech")
@Table(name = "project_tech")
public class ProjectTech {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "techName")
    private String techName;
    @ManyToOne
    @JoinColumn(name="projectID")
    private Project project;

    public ProjectTech() {
    }

    public ProjectTech(int id, String techName, Project project) {
        this.id = id;
        this.techName = techName;
        this.project = project;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTechName() {
        return techName;
    }

    public void setTechName(String techName) {
        this.techName = techName;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
