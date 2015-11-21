package com.huivip.holu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sunlaihui on 7/20/15.
 * DB:
 * SELECT TOP 1000 [ID]
 ,[DepartmentID]
 ,[DepartmentName]
 ,[ParentID]
 ,[DepartmentLevel]
 ,[DepartmentNote]
 ,[CompanyID]
 ,[PositionGPS]
 ,[CreateDate]
 FROM [Middatabase].[dbo].[R_Department]
 */
@Entity
@Table(name="R_Department")
@Indexed
@XmlRootElement
public class Department extends BaseObject implements Serializable {
    private Long id;
    private String departmentID;
    private String name;
    private String level;
    private String comment;
    private String positionGPS;
    private Date createDate;
    private Company company;
    private String pathName;
    private String style;
    private Department parent;
    private Set<Department> child=new HashSet<>();
    private Set<User> users=new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DocumentId
    @Column(name="ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name="DepartmentID")
    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    @Column(name="DepartmentName")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="DepartmentLevel")
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    @Column(name="DepartmentNote")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    @Column(name="PositionGPS")
    public String getPositionGPS() {
        return positionGPS;
    }

    public void setPositionGPS(String positionGPS) {
        this.positionGPS = positionGPS;
    }
    @Column(name="CreateDate")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    @ManyToOne
    @JoinColumn(name="CompanyID",referencedColumnName = "companyId")
    public Company getCompany() {
        return company;
    }
    @Column(name="DepartmentPathName")
    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }
    @Column(name = "DepartmentStyle")
    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    @ManyToMany
    @Fetch(FetchMode.SELECT)
    @JsonIgnore
    @JoinTable(
            name="R_DepartmentUserMappingTable",
            joinColumns = {@JoinColumn(name = "DepartmentId",referencedColumnName = "departmentID")},
            inverseJoinColumns = {@JoinColumn(name="UserID",referencedColumnName = "userID")}
    )
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
    @ManyToOne
    @JoinColumn(name="ParentID",referencedColumnName = "departmentID")
    @JsonIgnore
    public Department getParent() {
        return parent;
    }

    public void setParent(Department parent) {
        this.parent = parent;
    }
    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    public Set<Department> getChild() {
        return child;
    }

    public void setChild(Set<Department> child) {
        this.child = child;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (level != null ? !level.equals(that.level) : that.level != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (positionGPS != null ? !positionGPS.equals(that.positionGPS) : that.positionGPS != null) return false;
        return !(createDate != null ? !createDate.equals(that.createDate) : that.createDate != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (positionGPS != null ? positionGPS.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(this.departmentID)
                .toString();
    }
}
