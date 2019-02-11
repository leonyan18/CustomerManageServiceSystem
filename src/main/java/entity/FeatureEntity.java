package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "feature", schema = "CustomerServiceSystem", catalog = "")
public class FeatureEntity implements Serializable {
    private int fid;
    private Double val;
    private String score;
    private ModelEntity model;
    private ProblemEntity problem;

    @Id
    @Column(name = "fid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    @Basic
    @Column(name = "val")
    public Double getVal() {
        return val;
    }

    public void setVal(Double val) {
        this.val = val;
    }

    @Basic
    @Column(name = "score")
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeatureEntity that = (FeatureEntity) o;

        if (fid != that.fid) return false;
        if (val != null ? !val.equals(that.val) : that.val != null) return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fid;
        result = 31 * result + (val != null ? val.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "model_id", referencedColumnName = "mid")
    public ModelEntity getModel() {
        return model;
    }

    public void setModel(ModelEntity modelId) {
        this.model = modelId;
    }

    @OneToOne
    @JoinColumn(name = "problem_id", referencedColumnName = "pid")
    public ProblemEntity getProblem() {
        return problem;
    }

    public void setProblem(ProblemEntity problemId) {
        this.problem = problemId;
    }
}
