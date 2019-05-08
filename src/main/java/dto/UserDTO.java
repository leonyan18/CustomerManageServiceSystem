package dto;

import entity.UserEntity;
import entity.UserType;
import org.springframework.beans.BeanUtils;
import javax.xml.bind.JAXBException;

import java.util.Date;
import java.util.Random;

public class UserDTO {
    private int uid;
    private String username;
    private String name;
    private UserType type;
    private Date createTime;
    private Date lastLoginTime;
    private Double balance;
    private Double meanEvaluate;
    private String serveTimeofMonth;
    private Integer  serveTimesofMonth;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServeTimeofMonth() {
        return serveTimeofMonth;
    }

    public void setServeTimeofMonth(String serveTimeofMonth) {
        this.serveTimeofMonth = serveTimeofMonth;
    }

    public Integer getServeTimesofMonth() {
        return serveTimesofMonth;
    }

    public void setServeTimesofMonth(Integer serveTimesofMonth) {
        this.serveTimesofMonth = serveTimesofMonth;
    }

    public Double getMeanEvaluate() {
        return meanEvaluate;
    }

    public void setMeanEvaluate(Double meanEvaluate) {
        this.meanEvaluate = meanEvaluate;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public UserDTO() {
    }
    public UserDTO(UserEntity userEntity) {
        serveTimesofMonth= 0;
        serveTimeofMonth= "00:00:00";
        Random random=new Random();
        BeanUtils.copyProperties(userEntity,this);
        if(balance==null){
            meanEvaluate=0.0;
        }
        if(meanEvaluate==null){
            meanEvaluate=3.0;
        }
        if(lastLoginTime==null){
            lastLoginTime=new Date(System.currentTimeMillis()-1000000-random.nextInt(200000));
        }
        if(userEntity.getNewStatus()){
            meanEvaluate=0.0;
            serveTimesofMonth= random.nextInt(20);
            serveTimeofMonth= random.nextInt(20)+":"+random.nextInt(60)+":"+random.nextInt(60);
        }
    }
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", createTime=" + createTime +
                ", lastLoginTime=" + lastLoginTime +
                ", balance=" + balance +
                ", meanEvaluate=" + meanEvaluate +
                ", serveTimeofMonth='" + serveTimeofMonth + '\'' +
                ", serveTimesofMonth=" + serveTimesofMonth +
                '}';
    }
}
