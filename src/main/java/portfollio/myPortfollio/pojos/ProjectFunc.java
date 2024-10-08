package portfollio.myPortfollio.pojos;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
    @JoinColumn(name = "projectID", referencedColumnName = "projectID")
    @JsonBackReference
    Project project;
}
