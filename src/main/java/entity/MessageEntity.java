package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "message", schema = "CustomerServiceSystem", catalog = "")
public class MessageEntity implements Serializable {
    private int mid;
    private String content;
    private Timestamp sendtime;
    private ConversationEntity conversation;
    private UserEntity sender;

    @Id
    @Column(name = "mid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
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
    @Column(name = "sendtime")
    public Timestamp getSendtime() {
        return sendtime;
    }

    public void setSendtime(Timestamp sendtime) {
        this.sendtime = sendtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageEntity that = (MessageEntity) o;

        if (mid != that.mid) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (sendtime != null ? !sendtime.equals(that.sendtime) : that.sendtime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mid;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (sendtime != null ? sendtime.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "conversation_id", referencedColumnName = "cid")
    public ConversationEntity getConversation() {
        return conversation;
    }

    public void setConversation(ConversationEntity conversationId) {
        this.conversation = conversationId;
    }

    @OneToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "uid")
    public UserEntity getSender() {
        return sender;
    }

    public void setSender(UserEntity senderId) {
        this.sender = senderId;
    }
}
