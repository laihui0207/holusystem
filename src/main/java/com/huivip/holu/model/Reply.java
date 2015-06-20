package com.huivip.holu.model;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by sunlaihui on 6/15/15.
 */
@Entity
@Table(name="R_Reply")
@Indexed
@XmlRootElement
public class Reply extends BaseObject implements Serializable {
    private Long id;
    private String content;
    private PostBar postBar;
    private User replier;
    private Date replyTime=new Date();
    private Date updateTime=new Date();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DocumentId
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    @ManyToOne
    @JoinColumn(name = "postBarID")
    public PostBar getPostBar() {
        return postBar;
    }

    public void setPostBar(PostBar postBar) {
        this.postBar = postBar;
    }
    @ManyToOne
    @JoinColumn(name="replierID")
    public User getReplier() {
        return replier;
    }

    public void setReplier(User replier) {
        this.replier = replier;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reply reply = (Reply) o;

        if (id != null ? !id.equals(reply.id) : reply.id != null) return false;
        if (content != null ? !content.equals(reply.content) : reply.content != null) return false;
        if (postBar != null ? !postBar.equals(reply.postBar) : reply.postBar != null) return false;
        if (replier != null ? !replier.equals(reply.replier) : reply.replier != null) return false;
        if (replyTime != null ? !replyTime.equals(reply.replyTime) : reply.replyTime != null) return false;
        return !(updateTime != null ? !updateTime.equals(reply.updateTime) : reply.updateTime != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (postBar != null ? postBar.hashCode() : 0);
        result = 31 * result + (replier != null ? replier.hashCode() : 0);
        result = 31 * result + (replyTime != null ? replyTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", postBar=" + postBar +
                ", replier=" + replier +
                ", replyTime=" + replyTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
