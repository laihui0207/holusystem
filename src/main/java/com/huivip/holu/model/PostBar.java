package com.huivip.holu.model;

import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by sunlaihui on 6/15/15.
 */
@Entity
@Table(name="R_PostBar")
@Indexed
@XmlRootElement
public class PostBar extends BaseObject implements Serializable {
    private Long id;
    private String title;
    private String content;
    private Date createTime=new Date();
    private Date updateTime=new Date();
    private Timestamp lastReplyTime;
    private User lastReplyUser;
    private User creater;
    private String thumbnailUrl;
    private PostSubject postSubject;

    // if true, all user can reply the post, if false, just allow reply groups and reply user can reply the post
    private boolean ifAccessAllReply= true;
    private boolean ifAccessAllView= true;
    private Set<CustomGroup> replyGroups=new HashSet<>();
    private Set<User>  replyUsers=new HashSet<>();
    private Set<CustomGroup> viewGroups=new HashSet<>();
    private Set<User>  viewUsers=new HashSet<>();
    /*private List<Reply> replies=new ArrayList<>();*/

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
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Column(nullable = false,length = 8000)
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Timestamp getLastReplyTime() {
        return lastReplyTime;
    }

    public void setLastReplyTime(Timestamp lastReplyTime) {
        this.lastReplyTime = lastReplyTime;
    }
    @ManyToOne
    @JoinColumn(name="lastReplierID")
    public User getLastReplyUser() {
        return lastReplyUser;
    }

    public void setLastReplyUser(User lastReplyUser) {
        this.lastReplyUser = lastReplyUser;
    }
    @ManyToOne
    @JoinColumn(name = "createrID",updatable = false)
    public User getCreater() {
        return creater;
    }

    public void setCreater(User creater) {
        this.creater = creater;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
    @ManyToOne
    @JoinColumn(name="postSubjectID")
    public PostSubject getPostSubject() {
        return postSubject;
    }

    public void setPostSubject(PostSubject postSubject) {
        this.postSubject = postSubject;
    }

    public boolean isIfAccessAllReply() {
        return ifAccessAllReply;
    }

    public void setIfAccessAllReply(boolean ifAccessAllReply) {
        this.ifAccessAllReply = ifAccessAllReply;
    }
    @ManyToMany
    @JoinTable(
            name = "postBarReplyGroups",
            joinColumns = {@JoinColumn(name = "postbar_id")},
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    public Set<CustomGroup> getReplyGroups() {
        return replyGroups;
    }

    public void setReplyGroups(Set<CustomGroup> replyGroups) {
        this.replyGroups = replyGroups;
    }
    @ManyToMany
    @JoinTable(
            name = "postBarReplyUsers",
            joinColumns = {@JoinColumn(name = "postbar_id")},
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    public Set<User> getReplyUsers() {
        return replyUsers;
    }

    public void setReplyUsers(Set<User> replyUsers) {
        this.replyUsers = replyUsers;
    }
    /*@OneToMany(mappedBy = "postBar")
    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }*/

    public boolean isIfAccessAllView() {
        return ifAccessAllView;
    }

    public void setIfAccessAllView(boolean ifAccessAllView) {
        this.ifAccessAllView = ifAccessAllView;
    }

    @ManyToMany
    @JoinTable(
            name = "postBarViewGroups",
            joinColumns = {@JoinColumn(name = "postbar_id")},
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    public Set<CustomGroup> getViewGroups() {
        return viewGroups;
    }

    public void setViewGroups(Set<CustomGroup> viewGroups) {
        this.viewGroups = viewGroups;
    }
    @ManyToMany
    @JoinTable(
            name = "postBarViewUsers",
            joinColumns = {@JoinColumn(name = "postbar_id")},
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    public Set<User> getViewUsers() {
        return viewUsers;
    }

    public void setViewUsers(Set<User> viewUsers) {
        this.viewUsers = viewUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostBar postBar = (PostBar) o;

        if (ifAccessAllReply != postBar.ifAccessAllReply) return false;
        if (ifAccessAllView != postBar.ifAccessAllView) return false;
        if (id != null ? !id.equals(postBar.id) : postBar.id != null) return false;
        if (title != null ? !title.equals(postBar.title) : postBar.title != null) return false;
        if (content != null ? !content.equals(postBar.content) : postBar.content != null) return false;
        if (createTime != null ? !createTime.equals(postBar.createTime) : postBar.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(postBar.updateTime) : postBar.updateTime != null) return false;
        if (lastReplyTime != null ? !lastReplyTime.equals(postBar.lastReplyTime) : postBar.lastReplyTime != null)
            return false;
        if (lastReplyUser != null ? !lastReplyUser.equals(postBar.lastReplyUser) : postBar.lastReplyUser != null)
            return false;
        if (creater != null ? !creater.equals(postBar.creater) : postBar.creater != null) return false;
        if (thumbnailUrl != null ? !thumbnailUrl.equals(postBar.thumbnailUrl) : postBar.thumbnailUrl != null)
            return false;
        if (postSubject != null ? !postSubject.equals(postBar.postSubject) : postBar.postSubject != null) return false;
        if (replyGroups != null ? !replyGroups.equals(postBar.replyGroups) : postBar.replyGroups != null) return false;
        if (replyUsers != null ? !replyUsers.equals(postBar.replyUsers) : postBar.replyUsers != null) return false;
        if (viewGroups != null ? !viewGroups.equals(postBar.viewGroups) : postBar.viewGroups != null) return false;
        return !(viewUsers != null ? !viewUsers.equals(postBar.viewUsers) : postBar.viewUsers != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (lastReplyTime != null ? lastReplyTime.hashCode() : 0);
        result = 31 * result + (lastReplyUser != null ? lastReplyUser.hashCode() : 0);
        result = 31 * result + (creater != null ? creater.hashCode() : 0);
        result = 31 * result + (thumbnailUrl != null ? thumbnailUrl.hashCode() : 0);
        result = 31 * result + (postSubject != null ? postSubject.hashCode() : 0);
        result = 31 * result + (ifAccessAllReply ? 1 : 0);
        result = 31 * result + (ifAccessAllView ? 1 : 0);
        result = 31 * result + (replyGroups != null ? replyGroups.hashCode() : 0);
        result = 31 * result + (replyUsers != null ? replyUsers.hashCode() : 0);
        result = 31 * result + (viewGroups != null ? viewGroups.hashCode() : 0);
        result = 31 * result + (viewUsers != null ? viewUsers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PostBar{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", lastReplyTime=" + lastReplyTime +
                ", lastReplyUser=" + lastReplyUser +
                ", creater=" + creater +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", postSubject=" + postSubject +
                ", ifAccessAllReply=" + ifAccessAllReply +
                ", replyGroups=" + replyGroups +
                ", replyUsers=" + replyUsers +
                '}';
    }
}
