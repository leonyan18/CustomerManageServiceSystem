package DTO;

import entity.ConversationEntity;
import entity.MessageEntity;
import entity.UserEntity;
import org.springframework.beans.BeanUtils;

import java.util.Date;

public class MessageDTO {
    private int mid;
    private String content;
    private Date sendtime;
    private int conversationId;
    private UserDTO sender;
    private UserDTO receiver;

    public MessageDTO() {
    }

    public MessageDTO(MessageEntity messageEntity) {
        BeanUtils.copyProperties(messageEntity,this);
        this.sender=new UserDTO(messageEntity.getSender());
        this.receiver=new UserDTO(messageEntity.getReceiver());
        this.conversationId=messageEntity.getConversation().getCid();
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public UserDTO getSender() {
        return sender;
    }

    public void setSender(UserDTO sender) {
        this.sender = sender;
    }

    public UserDTO getReceiver() {
        return receiver;
    }

    public void setReceiver(UserDTO receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "mid=" + mid +
                ", content='" + content + '\'' +
                ", sendtime=" + sendtime +
                ", conversationId=" + conversationId +
                ", sender=" + sender +
                ", receiver=" + receiver +
                '}';
    }
}
