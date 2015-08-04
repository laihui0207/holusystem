package com.huivip.holu.model;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by sunlaihui on 8/3/15.
 * CREATE TABLE Middatabase.dbo.R_DictionaryForProcess (
 ID int NOT NULL,
 ProcessID nvarchar(50),
 ProcessStyle nvarchar(50),
 ChineseName nvarchar(50),
 EnglishName nvarchar(50),
 Note nvarchar(500),
 CreateDate datetime
 );
 */

@Entity
@Table(name = "R_DictionaryForProcess")
@Indexed
@XmlRootElement
public class ProcessDictionary extends BaseObject implements Serializable {
    Long id;
    String processID;
    String processStyle;
    String chineseName;
    String englishName;
    String note;
    Date createDate;

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name="ProcessID")
    public String getProcessID() {
        return processID;
    }

    public void setProcessID(String processID) {
        this.processID = processID;
    }
    @Column(name="ProcessStyle")
    public String getProcessStyle() {
        return processStyle;
    }

    public void setProcessStyle(String processStyle) {
        this.processStyle = processStyle;
    }
    @Column(name="ChineseName")
    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }
    @Column(name="EnglishName")
    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }
    @Column(name="Note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

        ProcessDictionary that = (ProcessDictionary) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (processID != null ? !processID.equals(that.processID) : that.processID != null) return false;
        if (processStyle != null ? !processStyle.equals(that.processStyle) : that.processStyle != null) return false;
        if (chineseName != null ? !chineseName.equals(that.chineseName) : that.chineseName != null) return false;
        if (englishName != null ? !englishName.equals(that.englishName) : that.englishName != null) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;
        return !(createDate != null ? !createDate.equals(that.createDate) : that.createDate != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (processID != null ? processID.hashCode() : 0);
        result = 31 * result + (processStyle != null ? processStyle.hashCode() : 0);
        result = 31 * result + (chineseName != null ? chineseName.hashCode() : 0);
        result = 31 * result + (englishName != null ? englishName.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProcessDictionary{" +
                "id=" + id +
                ", processID='" + processID + '\'' +
                ", processStyle='" + processStyle + '\'' +
                ", chineseName='" + chineseName + '\'' +
                ", englishName='" + englishName + '\'' +
                ", note='" + note + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
