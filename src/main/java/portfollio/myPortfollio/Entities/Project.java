package portfollio.myPortfollio.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "project")
@Table(name="project")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="projectID")
    int projectID;
    @Column(name = "title")
    String title;

    @Column(name = "subTitle")
    String subTitle;

    @Column(name = "purpose")
    String purpose;

    @Column(name = "link")
    String link;

    @Column(name="github")
    String github;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "projectID")
    List<ProjectTech> projectTechList;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "projectID")
    List<ProjectFunc> projectFunctionsList;

    public void addProjectTech(ProjectTech tempProjectTech){
        if (projectTechList == null){
            projectTechList = new ArrayList<>();
        }

        projectTechList.add(tempProjectTech);
//        tempProjectTech.setProject(this);
    }

}
