package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "greeting", schema = "CustomerServiceSystem", catalog = "")
public class GreetingEntity {
    private int gid;
    private String content;
    private Date createtime;
    private Date updatetime;
    private UserEntity createuser;

    @Id
    @Column(name = "gid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "createtime")
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Basic
    @Column(name = "updatetime")
    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GreetingEntity that = (GreetingEntity) o;
        return gid == that.gid &&
                Objects.equals(content, that.content) &&
                Objects.equals(createtime, that.createtime) &&
                Objects.equals(updatetime, that.updatetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gid, content, createtime, updatetime);
    }

    @ManyToOne
    @JoinColumn(name = "createuid", referencedColumnName = "uid")
    public UserEntity getCreateuser() {
        return createuser;
    }

    public void setCreateuser(UserEntity createuser) {
        this.createuser = createuser;
    }

    @Override
    public String toString() {
        return "GreetingEntity{" +
                "gid=" + gid +
                ", content='" + content + '\'' +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", createuser=" + createuser +
                '}';
    }
}
