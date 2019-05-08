package dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import entity.ConversationEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import service.MessageServiceImpl;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yan
 */
public class ConversationDTO implements Serializable {
    private static final Logger logger = LogManager.getLogger(ConversationDTO.class);
    private Integer cid;
    private Double evaluate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date starttime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endtime;
    private UserDTO customer;
    private UserDTO staff;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ConversationDTO{" +
                "cid=" + cid +
                ", evaluate=" + evaluate +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", customer=" + customer +
                ", staff=" + staff +
                ", status='" + status + '\'' +
                '}';
    }

    public ConversationDTO() {
    }

    public ConversationDTO(ConversationEntity conversationEntity) {
        logger.info(conversationEntity);
        BeanUtils.copyProperties(conversationEntity, this);
        if (evaluate==null){
            evaluate=3.0;
        }
        if(conversationEntity.getCustomer()!=null) {
            this.customer = new UserDTO(conversationEntity.getCustomer());
        }
        if(conversationEntity.getStaff()!=null) {
            this.staff = new UserDTO(conversationEntity.getStaff());
        }
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

    public void setCid(Integer cid) {
        this.cid = cid;
    }
}
