package com.huivip.holu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by sunlaihui on 8/25/15.
 */
@Entity
@Table(name="R_messagesReply")
@Indexed
@XmlRootElement
public class MessageReply extends BaseObject implements Serializable {

    private Long id;
    private Message message;
    private User replyUser;
    private String content;
    private Timestamp createTime;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @ManyToOne
    @JoinColumn(name="message")
    @JsonIgnore
    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
    @ManyToOne
    @JoinColumn(name="replyUser")
    public User getReplyUser() {
        return replyUser;
    }

    public void setReplyUser(User replyUser) {
        this.replyUser = replyUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageReply that = (MessageReply) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (replyUser != null ? !replyUser.equals(that.replyUser) : that.replyUser != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        return !(createTime != null ? !createTime.equals(that.createTime) : that.createTime != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (replyUser != null ? replyUser.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MessageReply{" +
                "id=" + id +
                ", message=" + message +
                ", replyUser=" + replyUser +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
