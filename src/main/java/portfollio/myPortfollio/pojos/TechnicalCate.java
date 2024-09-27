package portfollio.myPortfollio.pojos;

import java.util.List;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity(name = "technical_cate")
@Table(name = "technical_cate")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TechnicalCate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "techID")
    int techID;

    @Column(name = "techName")
    String techName;

    @OneToMany(mappedBy = "cate")
    List<TechDetail> techDetailList;
}
