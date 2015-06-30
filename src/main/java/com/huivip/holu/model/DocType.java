package com.huivip.holu.model;

import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

/**
 * Created by sunlaihui on 6/12/15.
 */
@Entity
@Table(name = "R_doctype")
@Indexed
@XmlRootElement
public class DocType extends BaseObject implements Serializable {
    Long id;
    String name;
    String comment;
    Timestamp createTime = new Timestamp(new Date().getTime());
    User creater;

   /* Set<Documentation> documentations;*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DocumentId
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    @Column(updatable = false)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    @ManyToOne
    @JoinColumn(name = "creater_id",updatable = false)
    public User getCreater() {
        return creater;
    }

    public void setCreater(User creater) {
        this.creater = creater;
    }
   /* @OneToMany(mappedBy = "docType")
    public Set<Documentation> getDocumentations() {
        return documentations;
    }

    public void setDocumentations(Set<Documentation> documents) {
        this.documentations = documents;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocType docType = (DocType) o;

        if (id != null ? !id.equals(docType.id) : docType.id != null) return false;
        if (name != null ? !name.equals(docType.name) : docType.name != null) return false;
        if (comment != null ? !comment.equals(docType.comment) : docType.comment != null) return false;
        if (createTime != null ? !createTime.equals(docType.createTime) : docType.createTime != null) return false;
        return !(creater != null ? !creater.equals(docType.creater) : docType.creater != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (creater != null ? creater.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DocType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", createTime=" + createTime +
                ", creater=" + creater +
                '}';
    }
}
