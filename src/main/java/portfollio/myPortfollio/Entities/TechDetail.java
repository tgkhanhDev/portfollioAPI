package portfollio.myPortfollio.Entities;

import jakarta.persistence.*;

@Entity(name = "tech_detail")
@Table(name = "tech_detail")
public class TechDetail {
    @Id
    @Column(name = "detailID")
    private int detailID;
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name="techID")
    private TechnicalCate cate;

    public TechDetail(int detailID, String description, TechnicalCate cate) {
        this.detailID = detailID;
        this.description = description;
        this.cate = cate;
    }

    public TechDetail() {
    }

    public int getDetailID() {
        return detailID;
    }

    public void setDetailID(int detailID) {
        this.detailID = detailID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TechnicalCate getCate() {
        return cate;
    }

    public void setCate(TechnicalCate cate) {
        this.cate = cate;
    }
}
