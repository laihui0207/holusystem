package com.huivip.holu.model;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by sunlaihui on 9/8/15.
 * ID int NOT NULL,
 UserID nvarchar(50),
 LoginDate datetime,
 InOrOut nvarchar(50),
 OptionTable nvarchar(50),
 GPS nvarchar(50),
 IP nvarchar(50)
 */
@Entity
@Table(name="R_UserTracking")
@Indexed
@XmlRootElement
public class UserTrack extends BaseObject implements Serializable {
    private Long id;
    private User user;
    private String inOrOut;
    private String optionTable;
    private String ip;
    private Date loginDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "userID")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getInOrOut() {
        return inOrOut;
    }

    public void setInOrOut(String inOrOut) {
        this.inOrOut = inOrOut;
    }

    public String getOptionTable() {
        return optionTable;
    }

    public void setOptionTable(String optionTable) {
        this.optionTable = optionTable;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTrack userTrack = (UserTrack) o;

        if (id != null ? !id.equals(userTrack.id) : userTrack.id != null) return false;
        if (user != null ? !user.equals(userTrack.user) : userTrack.user != null) return false;
        if (inOrOut != null ? !inOrOut.equals(userTrack.inOrOut) : userTrack.inOrOut != null) return false;
        if (optionTable != null ? !optionTable.equals(userTrack.optionTable) : userTrack.optionTable != null)
            return false;
        if (ip != null ? !ip.equals(userTrack.ip) : userTrack.ip != null) return false;
        return !(loginDate != null ? !loginDate.equals(userTrack.loginDate) : userTrack.loginDate != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (inOrOut != null ? inOrOut.hashCode() : 0);
        result = 31 * result + (optionTable != null ? optionTable.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (loginDate != null ? loginDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserTrack{" +
                "id=" + id +
                ", user=" + user +
                ", inOrOut='" + inOrOut + '\'' +
                ", optionTable='" + optionTable + '\'' +
                ", ip='" + ip + '\'' +
                ", loginDate=" + loginDate +
                '}';
    }
}
