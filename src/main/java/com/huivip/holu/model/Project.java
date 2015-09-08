package com.huivip.holu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CREATE TABLE MidDatabase.dbo.R_Project (
 ID int NOT NULL,
 ProjectID nvarchar(50),
 ProjectName nvarchar(50),
 ProjectLevel nvarchar(50),
 ParentID nvarchar(50),
 FullName nvarchar(50),
 TotalWeight float,
 TotalCost float,
 StartDate datetime,
 EndDate datetime,
 OwnerName nvarchar(50),
 BrokerName nvarchar(50),
 Note nvarchar(500),
 CompanyID nvarchar(50),
 CreateDate datetime
 );
 */
@Entity
@Table(name="R_Project")
@Indexed
@XmlRootElement
public class Project extends BaseObject implements Serializable {
    private Long id;
    private String projectID;
    private String projectName;
    private String projectLevel;
    private String fullName;
/*    private String parentID;*/
    private Long totalWeight;
    private Long totalCost;
    private Date startDate;
    private Date createDate;
    private Date endDate;
    private String ownerName;
    private String brokerName;
    private String note;
    private String projectPathName;
    private Company company;
    private Set<Department> departments=new HashSet<>();
    private Set<User> extendUsers=new HashSet<>();
    private Project parentProject;
    private Set<Project> childProjects=new HashSet<>();


    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DocumentId
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "ProjectID")
    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }
    @Column(name = "ProjectName")
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectFullName) {
        this.projectName = projectFullName;
    }
    @ManyToOne
    @JoinColumn(name = "CompanyID",referencedColumnName = "companyID")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    @Column(name="ProjectLevel")
    public String getProjectLevel() {
        return projectLevel;
    }

    public void setProjectLevel(String projectLevel) {
        this.projectLevel = projectLevel;
    }
    @Column(name="FullName")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    @Column(name="TotalWeight")
    public Long getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Long totalWeight) {
        this.totalWeight = totalWeight;
    }
    @Column(name="TotalCost")
    public Long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Long totalCost) {
        this.totalCost = totalCost;
    }
    @Column(name="StartDate")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    @Column(name="OwnerName")
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    @Column(name="BrokerName")
    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }
    @Column(name="Note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    @Column(name="EndDate")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
   /* @Column(name="ParentID")
    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }*/
    @Column(name="ProjectPathName")
    public String getProjectPathName() {
        return projectPathName;
    }

    public void setProjectPathName(String projectPathName) {
        this.projectPathName = projectPathName;
    }

    @ManyToMany
    @JoinTable(
            name="R_DepartmentProjectMappingTable",
            joinColumns = {@JoinColumn(name="ProjectID",referencedColumnName = "projectID")},
            inverseJoinColumns = {@JoinColumn(name="DepartmentID",referencedColumnName = "departmentID")}
    )
    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }
    @ManyToMany
    @JoinTable(
            name="R_UserNonProjectMappingTable",
            joinColumns = {@JoinColumn(name="ProjectID",referencedColumnName = "projectID")},
            inverseJoinColumns = {@JoinColumn(name="UserID",referencedColumnName = "userID")}
    )
    public Set<User> getExtendUsers() {
        return extendUsers;
    }

    public void setExtendUsers(Set<User> extendUsers) {
        this.extendUsers = extendUsers;
    }
    @ManyToOne
    @JoinColumn(name="ParentID",referencedColumnName = "projectID")
    @JsonIgnore
    public Project getParentProject() {
        return parentProject;
    }
    public void setParentProject(Project parentProject) {
        this.parentProject = parentProject;
    }
    @OneToMany(mappedBy = "parentProject")
    public Set<Project> getChildProjects() {
        return childProjects;
    }
    public void setChildProjects(Set<Project> childProjects) {
        this.childProjects = childProjects;
    }

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

        Project project = (Project) o;

        if (id != null ? !id.equals(project.id) : project.id != null) return false;
        if (projectID != null ? !projectID.equals(project.projectID) : project.projectID != null) return false;
        if (projectName != null ? !projectName.equals(project.projectName) : project.projectName != null) return false;
        if (projectLevel != null ? !projectLevel.equals(project.projectLevel) : project.projectLevel != null)
            return false;
        if (fullName != null ? !fullName.equals(project.fullName) : project.fullName != null) return false;
        if (totalWeight != null ? !totalWeight.equals(project.totalWeight) : project.totalWeight != null) return false;
        if (totalCost != null ? !totalCost.equals(project.totalCost) : project.totalCost != null) return false;
        if (startDate != null ? !startDate.equals(project.startDate) : project.startDate != null) return false;
        if (ownerName != null ? !ownerName.equals(project.ownerName) : project.ownerName != null) return false;
        if (brokerName != null ? !brokerName.equals(project.brokerName) : project.brokerName != null) return false;
        if (note != null ? !note.equals(project.note) : project.note != null) return false;
        if (company != null ? !company.equals(project.company) : project.company != null) return false;
        return !(departments != null ? !departments.equals(project.departments) : project.departments != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (projectID != null ? projectID.hashCode() : 0);
        result = 31 * result + (projectName != null ? projectName.hashCode() : 0);
        result = 31 * result + (projectLevel != null ? projectLevel.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (totalWeight != null ? totalWeight.hashCode() : 0);
        result = 31 * result + (totalCost != null ? totalCost.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (ownerName != null ? ownerName.hashCode() : 0);
        result = 31 * result + (brokerName != null ? brokerName.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (departments != null ? departments.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectID='" + projectID + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectLevel='" + projectLevel + '\'' +
                ", fullName='" + fullName + '\'' +
                ", totalWidght=" + totalWeight +
                ", totalCost=" + totalCost +
                ", startDate=" + startDate +
                ", ownerName='" + ownerName + '\'' +
                ", brokerName='" + brokerName + '\'' +
                ", note='" + note + '\'' +
                ", company=" + company +
                ", departments=" + departments +
                '}';
    }
}
