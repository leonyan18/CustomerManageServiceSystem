package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

/**
 * @author yan
 */
@Entity
@Table(name = "problem", schema = "CustomerServiceSystem", catalog = "")
public class ProblemEntity implements Serializable {
    private int pid;
    private String content;
    private String keywords;
    private AnswerEntity answer;
    private Date updateTime;
    private Date createTime;
    private ClassificationEntity classification;

    @Id
    @Column(name = "pid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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
    @Column(name = "keywords")
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "answer_id", referencedColumnName = "aid")
    public AnswerEntity getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerEntity answerId) {
        this.answer = answerId;
    }

    @Basic
    @Column(name = "update_time")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProblemEntity that = (ProblemEntity) o;

        if (pid != that.pid) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (keywords != null ? !keywords.equals(that.keywords) : that.keywords != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pid;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (keywords != null ? keywords.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ProblemEntity{" +
                "pid=" + pid +
                ", content='" + content + '\'' +
                ", keywords='" + keywords + '\'' +
                ", answer=" + answer +
                ", classification=" + classification +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "classification_id", referencedColumnName = "cid")
    public ClassificationEntity getClassification() {
        return classification;
    }

    public void setClassification(ClassificationEntity classification) {
        this.classification = classification;
    }
}
