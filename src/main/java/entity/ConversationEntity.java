package entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "conversation", schema = "CustomerServiceSystem", catalog = "")
public class ConversationEntity implements Serializable {
    private int cid;
    private Double evaluate;
    private String state;
    private Date starttime;
    private Date endtime;
    private UserEntity customer;
    private UserEntity staff;


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
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConversationEntity that = (ConversationEntity) o;

        if (cid != that.cid) return false;
        if (evaluate != null ? !evaluate.equals(that.evaluate) : that.evaluate != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (starttime != null ? !starttime.equals(that.starttime) : that.starttime != null) return false;
        if (endtime != null ? !endtime.equals(that.endtime) : that.endtime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cid;
        result = 31 * result + (evaluate != null ? evaluate.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (starttime != null ? starttime.hashCode() : 0);
        result = 31 * result + (endtime != null ? endtime.hashCode() : 0);
        return result;
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

    @Override
    public String toString() {
        return "ConversationEntity{" +
                "cid=" + cid +
                ", evaluate=" + evaluate +
                ", state='" + state + '\'' +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", customerid=" + customer +
                ", staffid=" + staff +
                '}';
    }
}
