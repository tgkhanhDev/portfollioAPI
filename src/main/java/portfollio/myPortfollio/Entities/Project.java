package portfollio.myPortfollio.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "project")
@Table(name="project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="projectID")
    private int projectID;
    @Column(name = "title")
    private String title;

    @Column(name = "subTitle")
    private String subTitle;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "link")
    private String link;

    @Column(name="github")
    private String github;

    @OneToMany(mappedBy = "project")
    private List<ProjectTech> projectTechList;

    @OneToMany(mappedBy = "project")
    private List<ProjectFunc> projectFunctionsList;

    public Project(int projectID, String title, String subTitle, String purpose, String link, String github, List<ProjectTech> projectTechList, List<ProjectFunc> projectFunctionsList) {
        this.projectID = projectID;
        this.title = title;
        this.subTitle = subTitle;
        this.purpose = purpose;
        this.link = link;
        this.github = github;
        this.projectTechList = projectTechList;
        this.projectFunctionsList = projectFunctionsList;
    }

    public Project() {
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public List<ProjectTech> getProjectTechList() {
        return projectTechList;
    }

    public void setProjectTechList(List<ProjectTech> projectTechList) {
        this.projectTechList = projectTechList;
    }

    public List<ProjectFunc> getProjectFunctionsList() {
        return projectFunctionsList;
    }

    public void setProjectFunctionsList(List<ProjectFunc> projectFunctionsList) {
        this.projectFunctionsList = projectFunctionsList;
    }
}
