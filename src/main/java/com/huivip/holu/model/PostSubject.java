package com.huivip.holu.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sunlaihui on 6/15/15.
 */
@Entity
@Table(name="R_PostSubject")
@Indexed
@XmlRootElement
public class PostSubject extends BaseObject implements Serializable {
    private Long id;
    private String name;
    private String comment;
    private Date createTime=new Date();
    private Date updateTime=new Date();
    private User creater;
    private User updater;
    /*private List<PostBar> posts=new ArrayList<>();*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DocumentId
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(nullable = false)
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    @Column(updatable = false)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="createrID",updatable = false)
    public User getCreater() {
        return creater;
    }

    public void setCreater(User creater) {
        this.creater = creater;
    }
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="updaterID")
    public User getUpdater() {
        return updater;
    }

    public void setUpdater(User updater) {
        this.updater = updater;
    }
   /* @OneToMany(mappedBy = "postSubject")
    public List<PostBar> getPosts() {
        return posts;
    }

    public void setPosts(List<PostBar> posts) {
        this.posts = posts;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostSubject that = (PostSubject) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (creater != null ? !creater.equals(that.creater) : that.creater != null) return false;
        return !(updater != null ? !updater.equals(that.updater) : that.updater != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (creater != null ? creater.hashCode() : 0);
        result = 31 * result + (updater != null ? updater.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PostSubject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", creater=" + creater +
                ", updater=" + updater +
                '}';
    }
}
