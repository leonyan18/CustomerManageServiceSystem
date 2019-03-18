package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bankcard", schema = "CustomerServiceSystem", catalog = "")
public class BankcardEntity implements Serializable {
    private int bid;
    private String bankcard;
    private UserEntity uid;

    @Id
    @Column(name = "bid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    @Basic
    @Column(name = "bankcard")
    public String getBankcard() {
        return bankcard;
    }

    public void setBankcard(String bankcard) {
        this.bankcard = bankcard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankcardEntity that = (BankcardEntity) o;

        if (bid != that.bid) return false;
        if (bankcard != null ? !bankcard.equals(that.bankcard) : that.bankcard != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bid;
        result = 31 * result + (bankcard != null ? bankcard.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid")
    public UserEntity getUid() {
        return uid;
    }

    public void setUid(UserEntity uid) {
        this.uid = uid;
    }
}
