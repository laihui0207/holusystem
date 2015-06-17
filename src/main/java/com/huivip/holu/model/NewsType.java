package com.huivip.holu.model;

import com.sun.istack.NotNull;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sunlaihui on 6/15/15.
 */
@Entity
@Table(name="R_newsType")
@Indexed
@XmlRootElement
public class NewsType extends BaseObject implements Serializable {

    private Long id;
    private String name;
    private String comment;
    private Timestamp createTime=new Timestamp(new Date().getTime());
    private Timestamp updateTime=new Timestamp(new Date().getTime());
    private User creater;
    private User updater;
    private List<News> news=new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Field(name = "newstypeid")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    @ManyToOne
    @JoinColumn(name="creater_id",updatable = false)
    public User getCreater() {
        return creater;
    }

    public void setCreater(User creater) {
        this.creater = creater;
    }
    @Column(updatable=false)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    @ManyToOne
    @JoinColumn(name = "updater_id")
    public User getUpdater() {
        return updater;
    }

    public void setUpdater(User updater) {
        this.updater = updater;
    }
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
    @IndexedEmbedded
    @OneToMany(mappedBy = "newsType")
    @Transient
    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }
    @PrePersist
    protected void onCreate() {
        createTime=new Timestamp(new Date().getTime());
    }
    @PreUpdate
    protected  void onUpdate(){
        updateTime=new Timestamp(new Date().getTime());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsType newsType = (NewsType) o;

        if (id != null ? !id.equals(newsType.id) : newsType.id != null) return false;
        if (name != null ? !name.equals(newsType.name) : newsType.name != null) return false;
        if (comment != null ? !comment.equals(newsType.comment) : newsType.comment != null) return false;
        if (createTime != null ? !createTime.equals(newsType.createTime) : newsType.createTime != null) return false;
        return !(updateTime != null ? !updateTime.equals(newsType.updateTime) : newsType.updateTime != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NewsType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
