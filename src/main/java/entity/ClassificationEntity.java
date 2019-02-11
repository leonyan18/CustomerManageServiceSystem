package entity;

import javax.persistence.*;

/**
 * @author yan
 */
@Entity
@Table(name = "classification", schema = "CustomerServiceSystem", catalog = "")
public class ClassificationEntity {
    private int cid;
    private String name;

    @Id
    @Column(name = "cid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassificationEntity that = (ClassificationEntity) o;

        if (cid != that.cid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cid;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClassificationEntity{" +
                "cid=" + cid +
                ", name='" + name + '\'' +
                '}';
    }
}
