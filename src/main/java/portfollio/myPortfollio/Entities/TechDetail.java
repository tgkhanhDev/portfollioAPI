package portfollio.myPortfollio.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity(name = "tech_detail")
@Table(name = "tech_detail")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TechDetail {
    @Id
    @Column(name = "detailID")
    int detailID;
    @Column(name = "description")
    String description;

    @ManyToOne
    @JoinColumn(name="techID")
    TechnicalCate cate;



}
