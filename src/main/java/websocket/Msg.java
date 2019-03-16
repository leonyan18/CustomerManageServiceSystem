package websocket;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;

public class Msg {
    private int from;
    private int to;
    private String content;
    private Date sendTime;
    private int cid;

    public Msg() {
        this.sendTime=new Date();
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "{" +
                "from=" + from +
                ", to=" + to +
                ", content='" + content + '\'' +
                ", sendTime=" + sendTime +
                ", cid=" + cid +
                '}';
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
