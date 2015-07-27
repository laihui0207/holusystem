package com.huivip.holu.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sunlaihui on 7/21/15.
 */
@Entity
@Table(name="R_Group")
@Indexed
@XmlRootElement
public class CustomGroup extends BaseObject implements Serializable{
    private Long id;
    private String groupID;
    private String name;
    private String comment;
    private User creater;
    private Department department;
    private Company company;
    private Date createDate=new Date();
    private Set<User> members=new HashSet<>();
    private Set<Project> projects=new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name="GroupID")
    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }
    @Column(name="GroupName")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name="GroupNote")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    @ManyToMany
    @Fetch(FetchMode.SELECT)
    @JoinTable(
            name="R_GroupUserMappingTable",
            joinColumns = {@JoinColumn(name="GroupID",referencedColumnName = "groupID")},
            inverseJoinColumns = {@JoinColumn(name = "UserID",referencedColumnName = "userID")}
    )
    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }
    @ManyToMany
    @Fetch(FetchMode.SELECT)
    @JoinTable(
            name="R_GroupProjectMappingTable",
            joinColumns = {@JoinColumn(name="GroupID",referencedColumnName = "groupID")},
            inverseJoinColumns = {@JoinColumn(name = "ProjectID",referencedColumnName = "projectID")}
    )
    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @ManyToOne
    @JoinColumn(name="CreateUserID",referencedColumnName = "userID")
    public User getCreater() {
        return creater;
    }

    public void setCreater(User creater) {
        this.creater = creater;
    }
    @ManyToOne
    @JoinColumn(name="DepartmentID",referencedColumnName = "departmentID")
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    @ManyToOne
    @JoinColumn(name="CompanyID",referencedColumnName = "companyId")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    @Column(name="CreateDate",updatable = false)
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

        CustomGroup CustomGroup = (CustomGroup) o;

        if (id != null ? !id.equals(CustomGroup.id) : CustomGroup.id != null) return false;
        if (groupID != null ? !groupID.equals(CustomGroup.groupID) : CustomGroup.groupID != null) return false;
        if (name != null ? !name.equals(CustomGroup.name) : CustomGroup.name != null) return false;
        if (comment != null ? !comment.equals(CustomGroup.comment) : CustomGroup.comment != null) return false;
        if (creater != null ? !creater.equals(CustomGroup.creater) : CustomGroup.creater != null) return false;
        if (department != null ? !department.equals(CustomGroup.department) : CustomGroup.department != null) return false;
        if (company != null ? !company.equals(CustomGroup.company) : CustomGroup.company != null) return false;
        return !(createDate != null ? !createDate.equals(CustomGroup.createDate) : CustomGroup.createDate != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (groupID != null ? groupID.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (creater != null ? creater.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupID='" + groupID + '\'' +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", creater=" + creater +
                ", department=" + department +
                ", company=" + company +
                ", createDate=" + createDate +
                '}';
    }
}
