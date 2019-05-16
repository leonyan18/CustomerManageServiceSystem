package websocket;

import java.util.Date;
import java.util.Objects;

public class Conversation {
    Date startTime;
    double motion;
    int weight;
    int cid;

    public Conversation() {
    }

    public Conversation(int cid) {
        this.cid = cid;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Conversation)) return false;
        Conversation that = (Conversation) o;
        return getCid() == that.getCid();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCid());
    }

    public Conversation(Date startTime, double motion, int expectedTime, int cid) {
        this.startTime = startTime;
        this.motion = motion;
        this.weight = expectedTime;
        this.cid = cid;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public double getMotion() {
        return motion;
    }

    public void setMotion(double motion) {
        this.motion = motion;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
