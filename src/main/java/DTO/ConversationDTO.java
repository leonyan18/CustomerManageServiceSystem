package DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import entity.ConversationEntity;
import entity.UserEntity;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yan
 */
public class ConversationDTO implements Serializable {
    private int cid;
    private Double evaluate;
    private String state;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date starttime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endtime;
    private UserDTO customer;
    private UserDTO staff;

    @Override
    public String toString() {
        return "ConversationDTO{" +
                "cid=" + cid +
                ", evaluate=" + evaluate +
                ", state='" + state + '\'' +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", customer=" + customer +
                ", staff=" + staff +
                '}';
    }

    public ConversationDTO() {
    }

    public ConversationDTO(ConversationEntity conversationEntity) {
        BeanUtils.copyProperties(conversationEntity,this);
        this.customer=new UserDTO(conversationEntity.getCustomer());
        this.staff=new UserDTO(conversationEntity.getStaff());
    }
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public Double getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(Double evaluate) {
        this.evaluate = evaluate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public UserDTO getCustomer() {
        return customer;
    }

    public void setCustomer(UserDTO customer) {
        this.customer = customer;
    }

    public UserDTO getStaff() {
        return staff;
    }

    public void setStaff(UserDTO staff) {
        this.staff = staff;
    }
}
