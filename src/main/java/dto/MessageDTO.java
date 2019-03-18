package dto;

import entity.MessageEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yan
 */
public class MessageDTO implements Serializable {
    private static final Logger logger = LogManager.getLogger(ConversationDTO.class);
    private Integer mid;
    private String content;
    private Date sendtime;
    private Integer conversationId;
    private UserDTO sender;
    private UserDTO receiver;

    public MessageDTO() {
    }

    public MessageDTO(MessageEntity messageEntity) {
        BeanUtils.copyProperties(messageEntity,this);
        if(messageEntity.getSender()!=null)
        this.sender=new UserDTO(messageEntity.getSender());
        if(messageEntity.getReceiver()!=null)
        this.receiver=new UserDTO(messageEntity.getReceiver());
        if(messageEntity.getConversation()!=null)
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

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public void setConversationId(Integer conversationId) {
        this.conversationId = conversationId;
    }
}
