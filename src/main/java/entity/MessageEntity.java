package entity;

import websocket.Msg;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "message", schema = "CustomerServiceSystem", catalog = "")
public class MessageEntity implements Serializable {
    private int mid;
    private String content;
    private Date sendtime;
    private ConversationEntity conversation;
    private UserEntity sender;
    private UserEntity receiver;

    public MessageEntity() {
    }

    public MessageEntity(Msg msg) {
        this.content=msg.getContent();
        this.sendtime=msg.getSendTime();
        UserEntity sender=new UserEntity();
        sender.setUid(msg.getFrom());
        this.sender=sender;
        UserEntity receiver=new UserEntity();
        receiver.setUid(msg.getTo());
        this.receiver=receiver;
        ConversationEntity conversationEntity=new ConversationEntity();
        conversationEntity.setCid(msg.getCid());
        this.conversation=conversationEntity;
    }

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
    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
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

    @OneToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "uid")
    public UserEntity getReceiver() {
        return receiver;
    }

    public void setReceiver(UserEntity receiver) {
        this.receiver = receiver;
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
}
