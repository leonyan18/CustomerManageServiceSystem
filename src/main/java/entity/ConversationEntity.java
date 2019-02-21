package entity;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "conversation", schema = "CustomerServiceSystem", catalog = "")
public class ConversationEntity implements Serializable {
    private int cid;
    private Double evaluate;
    private Date starttime;
    private Date endtime;
    private UserEntity customer;
    private UserEntity staff;
    private Boolean status;

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
    @Column(name = "evaluate")
    public Double getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(Double evaluate) {
        this.evaluate = evaluate;
    }


    @Basic
    @Column(name = "starttime")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    @Basic
    @Column(name = "endtime")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    @OneToOne
    @JoinColumn(name = "customerid", referencedColumnName = "uid")
    public UserEntity getCustomer() {
        return customer;
    }

    public void setCustomer(UserEntity customerid) {
        this.customer = customerid;
    }

    @OneToOne
    @JoinColumn(name = "staffid", referencedColumnName = "uid")
    public UserEntity getStaff() {
        return staff;
    }

    public void setStaff(UserEntity staffid) {
        this.staff = staffid;
    }

    @Basic
    @Column(name = "status")
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConversationEntity that = (ConversationEntity) o;

        if (cid != that.cid) return false;
        if (evaluate != null ? !evaluate.equals(that.evaluate) : that.evaluate != null) return false;
        if (starttime != null ? !starttime.equals(that.starttime) : that.starttime != null) return false;
        if (endtime != null ? !endtime.equals(that.endtime) : that.endtime != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cid;
        result = 31 * result + (evaluate != null ? evaluate.hashCode() : 0);
        result = 31 * result + (starttime != null ? starttime.hashCode() : 0);
        result = 31 * result + (endtime != null ? endtime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ConversationEntity{" +
                "cid=" + cid +
                ", evaluate=" + evaluate +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", customer=" + customer +
                ", staff=" + staff +
                ", status=" + status +
                '}';
    }
}
