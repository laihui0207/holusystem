package com.huivip.holu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by sunlaihui on 8/31/15.
 * CREATE TABLE holusystem.dbo.U_GS0000001_SummaryTable (
 ID int NOT NULL,
 ProjectID nvarchar(250),
 ProjectPathName nvarchar(250),
 RootProjectID nvarchar(250),
 ProjectLevel nvarchar(250),
 DepartmentID nvarchar(250),
 DepartmentName nvarchar(250),
 RootDepartmentID nvarchar(250),
 DepartmentLevel nvarchar(250),
 StyleID nvarchar(250),
 StyleName nvarchar(50),
 ProcessID nvarchar(250),
 ProcessName nvarchar(50),
 SumQuantity float,
 SumWeight float,
 SumRight float,
 SumDate nvarchar(50),
 SumWeek nvarchar(50),
 SumMonth nvarchar(50),
 SumYear nvarchar(50),
 PlanStart datetime,
 PlanEnd datetime,
 PredictStart datetime,
 PredictEnd datetime,
 ActualStart datetime,
 ActualEnd datetime,
 CreateUserID nvarchar(50),
 CreateDate datetime
 );

 */
@Entity
@Table(name = "U_SummaryTable")
@Indexed
@XmlRootElement
public class Summary extends BaseObject implements Serializable {
    Long id;
    Project project;
    Department department;
    String styleId;
    String styleName;
    String processId;
    String processName;
    float sumQuantity;
    float sumWeight;
    float sumRight;
    String sumDate;
    String sumWeek;
    String sumMonth;
    String sumYear;
    Timestamp planStart;
    Timestamp planEnd;
    Timestamp predictStart;
    Timestamp predictEnd;
    Timestamp actualStart;
    Timestamp actualEnd;
    User creater;
    Timestamp createDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @ManyToOne
    @JoinColumn(name="ProjectID",referencedColumnName = "projectID")
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    @ManyToOne
    @JoinColumn(name="DepartmentID",referencedColumnName = "departmentID")
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    @Column(name="StyleID")
    public String getStyleId() {
        return styleId;
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }
    @Column(name="StyleName")
    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }
    @Column(name="ProcessID")
    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }
    @Column(name="ProcessName")
    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }
    @Column(name="SumQuantity")
    public float getSumQuantity() {
        return sumQuantity;
    }

    public void setSumQuantity(float sumQuantity) {
        this.sumQuantity = sumQuantity;
    }
    @Column(name="SumWeight")
    public float getSumWeight() {
        return sumWeight;
    }

    public void setSumWeight(float subWeight) {
        this.sumWeight = subWeight;
    }
    @Column(name="SumRight")
    public float getSumRight() {
        return sumRight;
    }

    public void setSumRight(float sumRight) {
        this.sumRight = sumRight;
    }
    @Column(name="SumDate")
    public String getSumDate() {
        return sumDate;
    }

    public void setSumDate(String sumDate) {
        this.sumDate = sumDate;
    }
    @Column(name="SumWeek")
    public String getSumWeek() {
        return sumWeek;
    }

    public void setSumWeek(String sumWeek) {
        this.sumWeek = sumWeek;
    }
    @Column(name="SumMonth")
    public String getSumMonth() {
        return sumMonth;
    }

    public void setSumMonth(String sumMonth) {
        this.sumMonth = sumMonth;
    }
    @Column(name="SumYear")
    public String getSumYear() {
        return sumYear;
    }

    public void setSumYear(String sumYear) {
        this.sumYear = sumYear;
    }
    @Column(name="PlanStart")
    public Timestamp getPlanStart() {
        return planStart;
    }

    public void setPlanStart(Timestamp planStart) {
        this.planStart = planStart;
    }
    @Column(name="PlanEnd")
    public Timestamp getPlanEnd() {
        return planEnd;
    }

    public void setPlanEnd(Timestamp planEnd) {
        this.planEnd = planEnd;
    }
    @Column(name="predictStart")
    public Timestamp getPredictStart() {
        return predictStart;
    }

    public void setPredictStart(Timestamp predictStart) {
        this.predictStart = predictStart;
    }
    @Column(name="PredictEnd")
    public Timestamp getPredictEnd() {
        return predictEnd;
    }

    public void setPredictEnd(Timestamp predictEnd) {
        this.predictEnd = predictEnd;
    }
    @Column(name="ActualStart")
    public Timestamp getActualStart() {
        return actualStart;
    }

    public void setActualStart(Timestamp actualStart) {
        this.actualStart = actualStart;
    }

    public Timestamp getActualEnd() {
        return actualEnd;
    }

    public void setActualEnd(Timestamp actualEnd) {
        this.actualEnd = actualEnd;
    }
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="CreateUserID",referencedColumnName = "userID")
    public User getCreater() {
        return creater;
    }

    public void setCreater(User creater) {
        this.creater = creater;
    }
    @Column(name="CreateDate")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Summary summary = (Summary) o;

        if (Float.compare(summary.sumQuantity, sumQuantity) != 0) return false;
        if (Float.compare(summary.sumWeight, sumWeight) != 0) return false;
        if (Float.compare(summary.sumRight, sumRight) != 0) return false;
        if (id != null ? !id.equals(summary.id) : summary.id != null) return false;
        if (project != null ? !project.equals(summary.project) : summary.project != null) return false;
        if (department != null ? !department.equals(summary.department) : summary.department != null) return false;
        if (styleId != null ? !styleId.equals(summary.styleId) : summary.styleId != null) return false;
        if (styleName != null ? !styleName.equals(summary.styleName) : summary.styleName != null) return false;
        if (processId != null ? !processId.equals(summary.processId) : summary.processId != null) return false;
        if (processName != null ? !processName.equals(summary.processName) : summary.processName != null) return false;
        if (sumDate != null ? !sumDate.equals(summary.sumDate) : summary.sumDate != null) return false;
        if (sumWeek != null ? !sumWeek.equals(summary.sumWeek) : summary.sumWeek != null) return false;
        if (sumMonth != null ? !sumMonth.equals(summary.sumMonth) : summary.sumMonth != null) return false;
        if (sumYear != null ? !sumYear.equals(summary.sumYear) : summary.sumYear != null) return false;
        if (planStart != null ? !planStart.equals(summary.planStart) : summary.planStart != null) return false;
        if (planEnd != null ? !planEnd.equals(summary.planEnd) : summary.planEnd != null) return false;
        if (predictStart != null ? !predictStart.equals(summary.predictStart) : summary.predictStart != null)
            return false;
        if (predictEnd != null ? !predictEnd.equals(summary.predictEnd) : summary.predictEnd != null) return false;
        if (actualStart != null ? !actualStart.equals(summary.actualStart) : summary.actualStart != null) return false;
        if (actualEnd != null ? !actualEnd.equals(summary.actualEnd) : summary.actualEnd != null) return false;
        if (creater != null ? !creater.equals(summary.creater) : summary.creater != null) return false;
        return !(createDate != null ? !createDate.equals(summary.createDate) : summary.createDate != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (project != null ? project.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (styleId != null ? styleId.hashCode() : 0);
        result = 31 * result + (styleName != null ? styleName.hashCode() : 0);
        result = 31 * result + (processId != null ? processId.hashCode() : 0);
        result = 31 * result + (processName != null ? processName.hashCode() : 0);
        result = 31 * result + (sumQuantity != +0.0f ? Float.floatToIntBits(sumQuantity) : 0);
        result = 31 * result + (sumWeight != +0.0f ? Float.floatToIntBits(sumWeight) : 0);
        result = 31 * result + (sumRight != +0.0f ? Float.floatToIntBits(sumRight) : 0);
        result = 31 * result + (sumDate != null ? sumDate.hashCode() : 0);
        result = 31 * result + (sumWeek != null ? sumWeek.hashCode() : 0);
        result = 31 * result + (sumMonth != null ? sumMonth.hashCode() : 0);
        result = 31 * result + (sumYear != null ? sumYear.hashCode() : 0);
        result = 31 * result + (planStart != null ? planStart.hashCode() : 0);
        result = 31 * result + (planEnd != null ? planEnd.hashCode() : 0);
        result = 31 * result + (predictStart != null ? predictStart.hashCode() : 0);
        result = 31 * result + (predictEnd != null ? predictEnd.hashCode() : 0);
        result = 31 * result + (actualStart != null ? actualStart.hashCode() : 0);
        result = 31 * result + (actualEnd != null ? actualEnd.hashCode() : 0);
        result = 31 * result + (creater != null ? creater.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Summary{" +
                "id=" + id +
                ", project=" + project +
                ", department=" + department +
                ", styleId='" + styleId + '\'' +
                ", styleName='" + styleName + '\'' +
                ", processId='" + processId + '\'' +
                ", processName='" + processName + '\'' +
                ", sumQuantity=" + sumQuantity +
                ", subWeight=" + sumWeight +
                ", sumRight=" + sumRight +
                ", sumDate='" + sumDate + '\'' +
                ", sumWeek='" + sumWeek + '\'' +
                ", sumMonth='" + sumMonth + '\'' +
                ", sumYear='" + sumYear + '\'' +
                ", planStart=" + planStart +
                ", planEnd=" + planEnd +
                ", predictStart=" + predictStart +
                ", predictEnd=" + predictEnd +
                ", actualStart=" + actualStart +
                ", actualEnd=" + actualEnd +
                ", creater=" + creater +
                ", createDate=" + createDate +
                '}';
    }
}
