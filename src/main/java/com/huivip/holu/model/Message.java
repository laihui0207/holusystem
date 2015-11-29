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
 * Created by sunlaihui on 6/11/15.
 */
@Entity
@Table(name="R_messages")
@Indexed
@XmlRootElement
public class Message extends BaseObject implements Serializable {
    public final static String LIST_CACHE="Messages_";
    public final static String LIST_NEWS_CACHE_KEY="Messages_All_Keys";

    Long id;

    String title;
    String content;
    User creater;
    Timestamp createTime=new Timestamp(new Date().getTime());
    User Sender;
    User owner;
    Timestamp sendTime=new Timestamp(new Date().getTime());
    Set<User> receiveUsers;
    Set<CustomGroup> receiveGroups;
    int status;  // 0 create 1 sent to other, 2 receive and no read  3 received and have read
    User updater;
    Timestamp updateTime=new Timestamp(new Date().getTime());

    Set<MessageReply> replyList=new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DocumentId
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    @ManyToOne
    @JoinColumn(name = "creater_id",updatable = false)
    public User getCreater() {
        return creater;
    }

    public void setCreater(User creater) {
        this.creater = creater;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    @ManyToOne
    @JoinColumn(name = "sender_id")
    public User getSender() {
        return Sender;
    }

    public void setSender(User sender) {
        Sender = sender;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }
    @ManyToMany
    @JoinTable(
            name = "messagereceiveusers",
            joinColumns = {@JoinColumn(name = "message_id")},
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    public Set<User> getReceiveUsers() {
        return receiveUsers;
    }

    public void setReceiveUsers(Set<User> recerivers) {
        this.receiveUsers = recerivers;
    }
    @ManyToMany
    @JoinTable(
            name = "messagereceivegroups",
            joinColumns = {@JoinColumn(name = "message_id")},
            inverseJoinColumns = @JoinColumn(name = "usergroup_id")
    )
    public Set<CustomGroup> getReceiveGroups() {
        return receiveGroups;
    }

    public void setReceiveGroups(Set<CustomGroup> receriveGroups) {
        this.receiveGroups = receriveGroups;
    }
    @OneToMany(mappedBy = "message")
    public Set<MessageReply> getReplyList() {
        return replyList;
    }

    public void setReplyList(Set<MessageReply> replyList) {
        this.replyList = replyList;
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
    @ManyToOne
    @JoinColumn(name="owner_id")
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (status != message.status) return false;
        if (id != null ? !id.equals(message.id) : message.id != null) return false;
        if (title != null ? !title.equals(message.title) : message.title != null) return false;
        if (content != null ? !content.equals(message.content) : message.content != null) return false;
        if (creater != null ? !creater.equals(message.creater) : message.creater != null) return false;
        if (createTime != null ? !createTime.equals(message.createTime) : message.createTime != null) return false;
        if (Sender != null ? !Sender.equals(message.Sender) : message.Sender != null) return false;
        if (owner != null ? !owner.equals(message.owner) : message.owner != null) return false;
        if (sendTime != null ? !sendTime.equals(message.sendTime) : message.sendTime != null) return false;
        if (receiveUsers != null ? !receiveUsers.equals(message.receiveUsers) : message.receiveUsers != null)
            return false;
        if (receiveGroups != null ? !receiveGroups.equals(message.receiveGroups) : message.receiveGroups != null)
            return false;
        if (updater != null ? !updater.equals(message.updater) : message.updater != null) return false;
        return !(updateTime != null ? !updateTime.equals(message.updateTime) : message.updateTime != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (creater != null ? creater.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (Sender != null ? Sender.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (sendTime != null ? sendTime.hashCode() : 0);
        result = 31 * result + (receiveUsers != null ? receiveUsers.hashCode() : 0);
        result = 31 * result + (receiveGroups != null ? receiveGroups.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (updater != null ? updater.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", creater=" + creater +
                ", createTime=" + createTime +
                ", Sender=" + Sender +
                ", owner=" + owner +
                ", sendTime=" + sendTime +
                ", receiveUsers=" + receiveUsers +
                ", receiveGroups=" + receiveGroups +
                ", status=" + status +
                ", updater=" + updater +
                ", updateTime=" + updateTime +
                '}';
    }
}
