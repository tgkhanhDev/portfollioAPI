package portfollio.myPortfollio.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity(name = "project_functions")
@Table(name = "project_functions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectFunc {
    @Id
    @Column(name = "functionID")
    int functionID;

    @Column(name = "description")
    String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="projectID", referencedColumnName = "projectID")
    @JsonBackReference
    Project project;

}
