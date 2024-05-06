package portfollio.myPortfollio.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "technical_cate")
@Table(name = "technical_cate")
public class TechnicalCate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="techID")
    private int techID;

    @Column(name="techName")
    private String techName;

    @OneToMany(mappedBy = "cate")
    private List<TechDetail> techDetailList;

    public TechnicalCate() {
    }

    public TechnicalCate(int techID, String techName, List<TechDetail> techDetailList) {
        this.techID = techID;
        this.techName = techName;
        this.techDetailList = techDetailList;
    }

    public int getTechID() {
        return techID;
    }

    public void setTechID(int techID) {
        this.techID = techID;
    }

    public String getTechName() {
        return techName;
    }

    public void setTechName(String techName) {
        this.techName = techName;
    }

    public List<TechDetail> getTechDetailList() {
        return techDetailList;
    }

    public void setTechDetailList(List<TechDetail> techDetailList) {
        this.techDetailList = techDetailList;
    }
}
