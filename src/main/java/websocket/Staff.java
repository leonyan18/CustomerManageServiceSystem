package websocket;

import java.util.Objects;

public class Staff implements Comparable<Staff>{
    volatile int uid;
    volatile long allTime;
    volatile int sum;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Staff)) return false;
        Staff staff = (Staff) o;
        return getUid() == staff.getUid();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUid());
    }

    public Staff(int uid, int allTime, int sum) {
        this.uid = uid;
        this.allTime = allTime;
        this.sum = sum;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public long getAllTime() {
        return allTime;
    }

    public void setAllTime(long allTime) {
        this.allTime = allTime;
    }
    public synchronized void addConversation(Conversation o){
        allTime+=o.getStartTime().getTime()/1000+o.getWeight();
        sum++;
    }
    public synchronized void removeConversation(Conversation o){
        allTime-=o.getStartTime().getTime()/1000+o.getWeight();
        sum--;
    }
    @Override
    public int compareTo(Staff o) {
        if(this.sum == o.sum)
            return this.allTime > o.allTime ? 1 : -1;
        return this.sum > o.sum ? 1 : -1;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "uid=" + uid +
                ", allTime=" + allTime +
                ", sum=" + sum +
                '}';
    }
}
