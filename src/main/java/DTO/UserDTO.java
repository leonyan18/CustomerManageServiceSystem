package DTO;

import entity.UserEntity;
import entity.UserType;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import util.Important;

import java.sql.Date;

public class UserDTO {
    private int uid;
    private String username;
    private UserType type;
    private Date createTime;
    private Date lastLoginTime;
    private Double balance;
    private Double meanEvaluate;

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
        BeanUtils.copyProperties(userEntity,this);
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
                ", type=" + type +
                ", createTime=" + createTime +
                ", lastLoginTime=" + lastLoginTime +
                ", balance=" + balance +
                '}';
    }
}
