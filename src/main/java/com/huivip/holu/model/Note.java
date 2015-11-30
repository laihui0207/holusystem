package com.huivip.holu.model;

import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sunlaihui on 6/20/15.
 */
@Entity
@Table(name = "R_Note")
@Indexed
@XmlRootElement
public class Note extends BaseObject implements Serializable {

    public final static String LIST_CACHE="Notes_";
    public final static String LIST_NEWS_CACHE_KEY="Notes_All_Keys";

    Long id;
    String title;
    String content;
    User creater;
    Timestamp createTime=new Timestamp(new Date().getTime());
    User updater;
    Timestamp updateTime=new Timestamp(new Date().getTime());
    User fromUser;
    Timestamp receriveTime=new Timestamp(new Date().getTime());
    User receiver;
    Set<User> sendToUserList =new HashSet<>();
    Set<CustomGroup> sendToUserGroupList =new HashSet<>();

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
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Column(nullable = false,length = 8000)
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    @ManyToOne
    @JoinColumn(name = "createrID")
    public User getCreater() {
        return creater;
    }

    public void setCreater(User creater) {
        this.creater = creater;
    }
    @Column(updatable = false)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    @ManyToOne
    @JoinColumn(name="fromUserID")
    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public Timestamp getReceriveTime() {
        return receriveTime;
    }

    public void setReceriveTime(Timestamp receriveTime) {
        this.receriveTime = receriveTime;
    }
    @ManyToOne
    @JoinColumn(name="receiveUserID")
    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
    @ManyToOne
    @JoinColumn(name="updateUserID")
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
    @ManyToMany
    @JoinTable(
            name = "NoteReceiveUsers",
            joinColumns = {@JoinColumn(name = "note_id")},
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    public Set<User> getSendToUserList() {
        return sendToUserList;
    }

    public void setSendToUserList(Set<User> sendToUserList) {
        this.sendToUserList = sendToUserList;
    }
    @ManyToMany
    @JoinTable(
            name = "NoteReceiveUserGroups",
            joinColumns = {@JoinColumn(name = "note_id")},
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    public Set<CustomGroup> getSendToUserGroupList() {
        return sendToUserGroupList;
    }

    public void setSendToUserGroupList(Set<CustomGroup> sendToUserGroupList) {
        this.sendToUserGroupList = sendToUserGroupList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        if (id != null ? !id.equals(note.id) : note.id != null) return false;
        if (title != null ? !title.equals(note.title) : note.title != null) return false;
        if (content != null ? !content.equals(note.content) : note.content != null) return false;
        if (creater != null ? !creater.equals(note.creater) : note.creater != null) return false;
        if (createTime != null ? !createTime.equals(note.createTime) : note.createTime != null) return false;
        if (fromUser != null ? !fromUser.equals(note.fromUser) : note.fromUser != null) return false;
        if (receriveTime != null ? !receriveTime.equals(note.receriveTime) : note.receriveTime != null) return false;
        return !(receiver != null ? !receiver.equals(note.receiver) : note.receiver != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (creater != null ? creater.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (fromUser != null ? fromUser.hashCode() : 0);
        result = 31 * result + (receriveTime != null ? receriveTime.hashCode() : 0);
        result = 31 * result + (receiver != null ? receiver.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", creater=" + creater +
                ", createTime=" + createTime +
                ", fromUser=" + fromUser +
                ", receriveTime=" + receriveTime +
                ", receiver=" + receiver +
                '}';
    }
}
