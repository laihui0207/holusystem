package com.huivip.holu.model;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by sunlaihui on 9/20/15.
 *
 *
 * 	ID int NOT NULL,
 CompanyID nvarchar(250),
 UserID nvarchar(250),
 KeyArea nvarchar(250),
 KeyName nvarchar(250),
 KeyValue nvarchar(1000),
 KeyValue1 nvarchar(1000),
 KeyValue2 nvarchar(1000),
 KeyValue3 nvarchar(1000)
 */
@Entity
@Table(name = "R_Setting")
@Indexed
@XmlRootElement
public class Setting extends BaseObject implements Serializable {
    Long id;
    String companyID;
    String userID;
    String keyArea;
    String keyName;
    String KeyValue;
    String keyValue1;
    String keyValue2;
    String keyValue3;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "CompanyID")
    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }
    @Column(name="UserID")
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    @Column(name="KeyArea")
    public String getKeyArea() {
        return keyArea;
    }

    public void setKeyArea(String keyArea) {
        this.keyArea = keyArea;
    }
    @Column(name="KeyName")
    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }
    @Column(name="KeyValue")
    public String getKeyValue() {
        return KeyValue;
    }

    public void setKeyValue(String keyValue) {
        KeyValue = keyValue;
    }
    @Column(name="KeyValue1")
    public String getKeyValue1() {
        return keyValue1;
    }

    public void setKeyValue1(String keyValue1) {
        this.keyValue1 = keyValue1;
    }
    @Column(name="KeyValue2")
    public String getKeyValue2() {
        return keyValue2;
    }

    public void setKeyValue2(String keyValue2) {
        this.keyValue2 = keyValue2;
    }
    @Column(name="KeyValue3")
    public String getKeyValue3() {
        return keyValue3;
    }

    public void setKeyValue3(String keyValue3) {
        this.keyValue3 = keyValue3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Setting setting = (Setting) o;

        if (id != null ? !id.equals(setting.id) : setting.id != null) return false;
        if (companyID != null ? !companyID.equals(setting.companyID) : setting.companyID != null) return false;
        if (userID != null ? !userID.equals(setting.userID) : setting.userID != null) return false;
        if (keyArea != null ? !keyArea.equals(setting.keyArea) : setting.keyArea != null) return false;
        if (keyName != null ? !keyName.equals(setting.keyName) : setting.keyName != null) return false;
        if (KeyValue != null ? !KeyValue.equals(setting.KeyValue) : setting.KeyValue != null) return false;
        if (keyValue1 != null ? !keyValue1.equals(setting.keyValue1) : setting.keyValue1 != null) return false;
        if (keyValue2 != null ? !keyValue2.equals(setting.keyValue2) : setting.keyValue2 != null) return false;
        return !(keyValue3 != null ? !keyValue3.equals(setting.keyValue3) : setting.keyValue3 != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (companyID != null ? companyID.hashCode() : 0);
        result = 31 * result + (userID != null ? userID.hashCode() : 0);
        result = 31 * result + (keyArea != null ? keyArea.hashCode() : 0);
        result = 31 * result + (keyName != null ? keyName.hashCode() : 0);
        result = 31 * result + (KeyValue != null ? KeyValue.hashCode() : 0);
        result = 31 * result + (keyValue1 != null ? keyValue1.hashCode() : 0);
        result = 31 * result + (keyValue2 != null ? keyValue2.hashCode() : 0);
        result = 31 * result + (keyValue3 != null ? keyValue3.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Setting{" +
                "id=" + id +
                ", companyID='" + companyID + '\'' +
                ", userID='" + userID + '\'' +
                ", keyArea='" + keyArea + '\'' +
                ", keyName='" + keyName + '\'' +
                ", KeyValue='" + KeyValue + '\'' +
                ", keyValue1='" + keyValue1 + '\'' +
                ", keyValue2='" + keyValue2 + '\'' +
                ", keyValue3='" + keyValue3 + '\'' +
                '}';
    }
}
