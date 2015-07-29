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
 * CREATE TABLE Middatabase.dbo.U_HOLU_SubComponentList (
 ID int NOT NULL,
 ComponentID nvarchar(50),
 SubComponentID nvarchar(50),
 SubComponentName nvarchar(50),
 SubQuantity int,
 UserID nvarchar(50),
 CreateDate datetime
 );
 */
@Entity
@Table(name = "U_HOLU_SubComponentList")
@Indexed
@XmlRootElement
public class SubComponentList extends BaseObject implements Serializable {
    Long id;
    String componentID;
    String subComponentID;
    String subComponentName;
    int subQuantity;
    User user;
    Date createDate;

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
    @Column(name="ComponentID")
    public String getComponentID() {
        return componentID;
    }

    public void setComponentID(String componentID) {
        this.componentID = componentID;
    }
    @Column(name="SubComponentID")
    public String getSubComponentID() {
        return subComponentID;
    }

    public void setSubComponentID(String subComponentID) {
        this.subComponentID = subComponentID;
    }
    @Column(name="SubComponentName")
    public String getSubComponentName() {
        return subComponentName;
    }

    public void setSubComponentName(String subComponentName) {
        this.subComponentName = subComponentName;
    }
    @Column(name="SubQuantity")
    public int getSubQuantity() {
        return subQuantity;
    }

    public void setSubQuantity(int subQuantity) {
        this.subQuantity = subQuantity;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubComponentList that = (SubComponentList) o;

        if (subQuantity != that.subQuantity) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (componentID != null ? !componentID.equals(that.componentID) : that.componentID != null) return false;
        if (subComponentID != null ? !subComponentID.equals(that.subComponentID) : that.subComponentID != null)
            return false;
        if (subComponentName != null ? !subComponentName.equals(that.subComponentName) : that.subComponentName != null)
            return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        return !(createDate != null ? !createDate.equals(that.createDate) : that.createDate != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (componentID != null ? componentID.hashCode() : 0);
        result = 31 * result + (subComponentID != null ? subComponentID.hashCode() : 0);
        result = 31 * result + (subComponentName != null ? subComponentName.hashCode() : 0);
        result = 31 * result + subQuantity;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SubComponentList{" +
                "id=" + id +
                ", componentID='" + componentID + '\'' +
                ", subComponentID='" + subComponentID + '\'' +
                ", subComponentName='" + subComponentName + '\'' +
                ", subQuantity=" + subQuantity +
                ", user=" + user +
                ", createDate=" + createDate +
                '}';
    }
}
