package com.huivip.holu.model;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by sunlaihui on 6/15/15.
 * SELECT TOP 1000 [ID]
 ,[ProjectID]
 ,[ProjectFullName]
 ,[UnitFullName]
 ,[BatchFullName]
 ,[ProjectShortName]
 ,[UnitShortName]
 ,[BatchShortName]
 ,[OwnerID]
 ,[CompanyID]
 FROM [MidDatabase].[dbo].[R_Project]
 */
@Entity
@Table(name="R_Project")
@Indexed
@XmlRootElement
public class Project extends BaseObject implements Serializable {
    private Long id;
    private String projectID;
    private String projectFullName;
    private String unitFullName;
    private String batchFullName;
    private String projectShortName;
    private String unitShortName;
    private String batchShortName;
    private User Owner;
    private Company company;
    private String companyID;

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
    @Column(name = "ProjectFullName")
    public String getProjectFullName() {
        return projectFullName;
    }

    public void setProjectFullName(String projectFullName) {
        this.projectFullName = projectFullName;
    }
    @Column(name = "UnitFullName")
    public String getUnitFullName() {
        return unitFullName;
    }

    public void setUnitFullName(String unitFullName) {
        this.unitFullName = unitFullName;
    }
    @Column(name="BatchFullName")
    public String getBatchFullName() {
        return batchFullName;
    }

    public void setBatchFullName(String batchFullName) {
        this.batchFullName = batchFullName;
    }
    @Column(name="ProjectShortName")
    public String getProjectShortName() {
        return projectShortName;
    }

    public void setProjectShortName(String projectShortName) {
        this.projectShortName = projectShortName;
    }
    @Column(name="UnitShortName")
    public String getUnitShortName() {
        return unitShortName;
    }

    public void setUnitShortName(String unitShortName) {
        this.unitShortName = unitShortName;
    }
    @Column(name="BatchShortName")
    public String getBatchShortName() {
        return batchShortName;
    }

    public void setBatchShortName(String batchShortName) {
        this.batchShortName = batchShortName;
    }
    @ManyToOne
    @JoinColumn(name="OwnerID")
    public User getOwner() {
        return Owner;
    }

    public void setOwner(User owner) {
        Owner = owner;
    }
    @ManyToOne
    @JoinColumn(name = "Company_id")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (id != null ? !id.equals(project.id) : project.id != null) return false;
        if (projectID != null ? !projectID.equals(project.projectID) : project.projectID != null) return false;
        if (projectFullName != null ? !projectFullName.equals(project.projectFullName) : project.projectFullName != null)
            return false;
        if (unitFullName != null ? !unitFullName.equals(project.unitFullName) : project.unitFullName != null)
            return false;
        if (batchFullName != null ? !batchFullName.equals(project.batchFullName) : project.batchFullName != null)
            return false;
        if (projectShortName != null ? !projectShortName.equals(project.projectShortName) : project.projectShortName != null)
            return false;
        if (unitShortName != null ? !unitShortName.equals(project.unitShortName) : project.unitShortName != null)
            return false;
        if (batchShortName != null ? !batchShortName.equals(project.batchShortName) : project.batchShortName != null)
            return false;
        if (Owner != null ? !Owner.equals(project.Owner) : project.Owner != null) return false;
        return !(company != null ? !company.equals(project.company) : project.company != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (projectID != null ? projectID.hashCode() : 0);
        result = 31 * result + (projectFullName != null ? projectFullName.hashCode() : 0);
        result = 31 * result + (unitFullName != null ? unitFullName.hashCode() : 0);
        result = 31 * result + (batchFullName != null ? batchFullName.hashCode() : 0);
        result = 31 * result + (projectShortName != null ? projectShortName.hashCode() : 0);
        result = 31 * result + (unitShortName != null ? unitShortName.hashCode() : 0);
        result = 31 * result + (batchShortName != null ? batchShortName.hashCode() : 0);
        result = 31 * result + (Owner != null ? Owner.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectID='" + projectID + '\'' +
                ", projectFullName='" + projectFullName + '\'' +
                ", unitFullName='" + unitFullName + '\'' +
                ", batchFullName='" + batchFullName + '\'' +
                ", projectShortName='" + projectShortName + '\'' +
                ", unitShortName='" + unitShortName + '\'' +
                ", batchShortName='" + batchShortName + '\'' +
                ", Owner=" + Owner +
                ", company=" + company +
                '}';
    }
}
