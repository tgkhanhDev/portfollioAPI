package portfollio.myPortfollio.pojos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity(name="project_tech")
@Table(name = "project_tech")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectTech {
    @Id
    @Column(name = "id")
    int id;

//    @Column(name = "techName")
    String techName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="projectID", referencedColumnName = "projectID")
    @JsonBackReference
    Project project;
}
