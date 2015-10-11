package com.huivip.holu.model;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by sunlaihui on 7/29/15.
 *
 * CREATE TABLE Middatabase.dbo.U_HOLU_ProcessMidTable (
 ID int NOT NULL,
 SubComponentID nvarchar(50),
 StyleProcessID nvarchar(50),
 ProcessNote nvarchar(500),
 StartDate datetime,
 EndDate datetime,
 UserID nvarchar(50),
 CreateDate datetime,
 PostionGPS nvarchar(50)
 );
 */

@Entity
@Table(name = "U_ProcessMidTable")
@Indexed
@XmlRootElement
public class ProcessMid extends BaseObject implements Serializable {
    Long id;
    String subComponentID;
    String styleProcessID;
    String processNote;
    Date startDate;
    Date endDate;
    User user;
    Date createDate=new Date();
    String positionGPS;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DocumentId
    @Column(name="ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "SubComponentID")
    public String getSubComponentID() {
        return subComponentID;
    }

    public void setSubComponentID(String subComponentID) {
        this.subComponentID = subComponentID;
    }
    @Column(name="StyleProcessID")
    public String getStyleProcessID() {
        return styleProcessID;
    }
    public void setStyleProcessID(String styleProcessID) {
        this.styleProcessID = styleProcessID;
    }
    @Column(name="ProcessNote")
    public String getProcessNote() {
        return processNote;
    }

    public void setProcessNote(String processNote) {
        this.processNote = processNote;
    }
    @Column(name="StartDate")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    @Column(name="EndDate")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    @ManyToOne
    @JoinColumn(name="UserID",referencedColumnName = "userID")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @Column(name="CreateDate")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    @Column(name="PostionGPS")
    public String getPositionGPS() {
        return positionGPS;
    }

    public void setPositionGPS(String positionGPS) {
        this.positionGPS = positionGPS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProcessMid that = (ProcessMid) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (subComponentID != null ? !subComponentID.equals(that.subComponentID) : that.subComponentID != null)
            return false;
        if (styleProcessID != null ? !styleProcessID.equals(that.styleProcessID) : that.styleProcessID != null)
            return false;
        if (processNote != null ? !processNote.equals(that.processNote) : that.processNote != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        return !(positionGPS != null ? !positionGPS.equals(that.positionGPS) : that.positionGPS != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (subComponentID != null ? subComponentID.hashCode() : 0);
        result = 31 * result + (styleProcessID != null ? styleProcessID.hashCode() : 0);
        result = 31 * result + (processNote != null ? processNote.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (positionGPS != null ? positionGPS.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProcessMid{" +
                "id=" + id +
                ", subComponentID='" + subComponentID + '\'' +
                ", styleProcessID='" + styleProcessID + '\'' +
                ", processNote='" + processNote + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", user=" + user +
                ", createDate=" + createDate +
                ", postionGPS='" + positionGPS + '\'' +
                '}';
    }
}
