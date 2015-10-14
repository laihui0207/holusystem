package com.huivip.holu.model;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sunlaihui on 9/10/15.\
 * 	ID int NOT NULL,
 TaskID nvarchar(50),
 TaskName nvarchar(50),
 TaskStyle nvarchar(50),
 StartDate datetime,
 EndDate datetime,
 ProjectID nvarchar(50),
 ProcessID nvarchar(50),
 SubComponentID nvarchar(1073741823),
 SubComponentName
 RecordID nvarchar(1073741823),
 StartCount float,
 EndCount float,
 AcceptPush int,
 CreateUserID nvarchar(50),
 CreateDate datetime
 */
@Entity
@Table(name = "U_TaskTable")
@Indexed
@XmlRootElement
public class Task extends BaseObject implements Serializable {
    private Long id;
    private String taskId;
    private String taskStyle;
    private String taskName;
    /*private Date startDate;
    private Date endDate;*/
    private Date taskDate;
    private Project project;
    private ProcessDictionary processDictionary;
    private String subComponentIdList;
    private String subComponentNameList;
    private List<SubComponentList> subComponents=new ArrayList<>();
    /*Long startCount;
    Long endCount;*/
    Long taskCount;
    Long taskWeight;
    Long taskPercent;
    int acceptPush;
    User createUser;
    Date createDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name="TaskID")
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    @Column(name="TaskStyle")
    public String getTaskStyle() {
        return taskStyle;
    }

    public void setTaskStyle(String taskStyle) {
        this.taskStyle = taskStyle;
    }
    @Column(name="TaskName")
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    @Column(name="TaskDate")
    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }
    @Column(name="TaskPercent")
    public Long getTaskPercent() {
        return taskPercent;
    }

    public void setTaskPercent(Long taskPrecent) {
        this.taskPercent = taskPrecent;
    }

    /*  @Column(name="StartDate")
            public Date getStartDate() {
                return startDate;
            }

            public void setStartDate(Date startDate) {
                this.startDate = startDate;
            }
            @Column(name="EndDate")
            public Date getEndDate() {
                return endDate;
            }

            public void setEndDate(Date endDate) {
                this.endDate = endDate;
            }*/
    @ManyToOne
    @JoinColumn(name="ProjectID",referencedColumnName = "projectID")
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    @ManyToOne
    @JoinColumn(name="ProcessID",referencedColumnName = "processID")
    public ProcessDictionary getProcessDictionary() {
        return processDictionary;
    }

    public void setProcessDictionary(ProcessDictionary processDictionary) {
        this.processDictionary = processDictionary;
    }
    @Transient
    public List<SubComponentList> getSubComponents() {
        return subComponents;
    }

    public void setSubComponents(List<SubComponentList> subComponents) {
        this.subComponents = subComponents;
    }

   /* @Column(name="RecordID")
    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }*/
    /*@Column(name="StartCount")
    public Long getStartCount() {
        return startCount;
    }

    public void setStartCount(Long startCount) {
        this.startCount = startCount;
    }
    @Column(name = "EndCount")
    public Long getEndCount() {
        return endCount;
    }

    public void setEndCount(Long endCount) {
        this.endCount = endCount;
    }*/
    @Column(name="AcceptPush")
    public int getAcceptPush() {
        return acceptPush;
    }

    public void setAcceptPush(int acceptPush) {
        this.acceptPush = acceptPush;
    }
    @ManyToOne
    @JoinColumn(name="CreateUserID",referencedColumnName = "userID")
    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }
    @Column(name="CreateDate")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    @Column(name="SubComponentID")
    public String getSubComponentIdList() {
        return subComponentIdList;
    }

    public void setSubComponentIdList(String subComponentIdList) {
        this.subComponentIdList = subComponentIdList;
    }
    @Column(name="SubComponentName")
    public String getSubComponentNameList() {
        return subComponentNameList;
    }

    public void setSubComponentNameList(String subComponentNameList) {
        this.subComponentNameList = subComponentNameList;
    }
    @Column(name="TaskCount")
    public Long getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(Long taskCount) {
        this.taskCount = taskCount;
    }
    @Column(name="TaskWeight")
    public Long getTaskWeight() {
        return taskWeight;
    }

    public void setTaskWeight(Long taskWeight) {
        this.taskWeight = taskWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (acceptPush != task.acceptPush) return false;
        if (id != null ? !id.equals(task.id) : task.id != null) return false;
        if (taskId != null ? !taskId.equals(task.taskId) : task.taskId != null) return false;
        if (taskStyle != null ? !taskStyle.equals(task.taskStyle) : task.taskStyle != null) return false;
        if (taskName != null ? !taskName.equals(task.taskName) : task.taskName != null) return false;
        if (project != null ? !project.equals(task.project) : task.project != null) return false;
        if (processDictionary != null ? !processDictionary.equals(task.processDictionary) : task.processDictionary != null)
            return false;
        if (createUser != null ? !createUser.equals(task.createUser) : task.createUser != null) return false;
        return !(createDate != null ? !createDate.equals(task.createDate) : task.createDate != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (taskId != null ? taskId.hashCode() : 0);
        result = 31 * result + (taskStyle != null ? taskStyle.hashCode() : 0);
        result = 31 * result + (taskName != null ? taskName.hashCode() : 0);
        result = 31 * result + (project != null ? project.hashCode() : 0);
        result = 31 * result + (processDictionary != null ? processDictionary.hashCode() : 0);
        result = 31 * result + acceptPush;
        result = 31 * result + (createUser != null ? createUser.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskId='" + taskId + '\'' +
                ", taskStyle='" + taskStyle + '\'' +
                ", taskName='" + taskName + '\'' +
                ", project=" + project +
                ", processDictionary=" + processDictionary +
                ", acceptPush=" + acceptPush +
                ", createUser=" + createUser +
                ", createDate=" + createDate +
                '}';
    }
}
