package com.huivip.holu.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by sunlaihui on 6/15/15.
 *
 * SELECT TOP 1000 [ID]
 ,[PostID]
 ,[PostName]
 ,[ProcessName]
 ,[PostNote]
 ,[CompanyID]
 ,[CreateDate]
 FROM [MidDatabase].[dbo].[R_Post]
 */
@Entity
@Table(name="R_Post")
@Indexed
@XmlRootElement
public class Post extends BaseObject implements Serializable {
    private Long id;
    private String postID;
    private String postName;
/*    private String processID;*/
    ProcessDictionary processDictionary;
    private String postNote;
    private Company company;
    private Date createDate=new Date();

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
    @Column(name="PostID")
    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }
    @Column(name="PostName")
    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }
   /* @Column(name="ProcessID")
    public String getProcessID() {
        return processID;
    }

    public void setProcessID(String processName) {
        this.processID = processName;
    }*/
    @ManyToOne
    @JoinColumn(name="ProcessID",referencedColumnName = "processID")
    public ProcessDictionary getProcessDictionary() {
        return processDictionary;
    }

    public void setProcessDictionary(ProcessDictionary processDictionary) {
        this.processDictionary = processDictionary;
    }

    @Column(name="PostNote")
    public String getPostNote() {
        return postNote;
    }

    public void setPostNote(String postNote) {
        this.postNote = postNote;
    }
    @ManyToOne
    @JoinColumn(name="CompanyID",referencedColumnName = "companyId")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

        Post post = (Post) o;

        if (id != null ? !id.equals(post.id) : post.id != null) return false;
        if (postID != null ? !postID.equals(post.postID) : post.postID != null) return false;
        if (postName != null ? !postName.equals(post.postName) : post.postName != null) return false;
        if (postNote != null ? !postNote.equals(post.postNote) : post.postNote != null) return false;
        if (company != null ? !company.equals(post.company) : post.company != null) return false;
        return !(createDate != null ? !createDate.equals(post.createDate) : post.createDate != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (postID != null ? postID.hashCode() : 0);
        result = 31 * result + (postName != null ? postName.hashCode() : 0);
        result = 31 * result + (postNote != null ? postNote.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(this.postName)
                .toString();
    }
}
