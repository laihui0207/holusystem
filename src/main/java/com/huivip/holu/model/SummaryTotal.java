package com.huivip.holu.model;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by sunlaihui on 9/18/15.
 *
 * ID int NOT NULL,
 curDate datetime,
 ItemName nvarchar(250),
 ItemID nvarchar(250),
 ItemStyle nvarchar(250),
 StartOrEnd nvarchar(250),
 ActualPlanPredict nvarchar(250),
 ProjectID nvarchar(250),
 ProjectPathName nvarchar(250),
 ProjectRootID nvarchar(250),
 ProjectLevel nvarchar(250),
 DepartmentID nvarchar(250),
 DepartmentPathName nvarchar(250),
 DepartmentRootID nvarchar(250),
 DepartmentLevel nvarchar(250),
 ProcessID nvarchar(250),
 ProcessName nvarchar(250),
 SumCount float,
 SumWeight float,
 SumRight float,
 SumDate nvarchar(250),
 SumWeek nvarchar(250),
 SumMonth nvarchar(250),
 SumYear nvarchar(250),
 CreateUserID nvarchar(250),
 CreateDate datetime
 */
@Entity
@Table(name = "U_SummaryTotalTable")
@Indexed
@XmlRootElement
public class SummaryTotal extends  BaseObject implements Serializable {
    Long id;
    Date curDate;
    String itemName;
    String itemID;
    String itemStyle;
    String startOrEnd;
    String actualPlanPredict;
    String projectID;
    String projectPathName;
    String projectRootID;
    String projectLevel;
    String departmentID;
    String departmentPathName;
    String departmentRootID;
    String processID;
    String processName;
    float sumCount;
    float sumWeight;
    float sumRight;
    String sumDate;
    String sumWeek;
    String sumMonth;
    String sumYear;
    String createUserID;
    Date createDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name="curDate")
    public Date getCurDate() {
        return curDate;
    }

    public void setCurDate(Date curDate) {
        this.curDate = curDate;
    }
    @Column(name="ItemName")
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    @Column(name="ItemID")
    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }
    @Column(name="ItemStyle")
    public String getItemStyle() {
        return itemStyle;
    }

    public void setItemStyle(String itemStyle) {
        this.itemStyle = itemStyle;
    }
    @Column(name="StartOrEnd")
    public String getStartOrEnd() {
        return startOrEnd;
    }

    public void setStartOrEnd(String startOrEnd) {
        this.startOrEnd = startOrEnd;
    }
    @Column(name="ActualPlanPredict")
    public String getActualPlanPredict() {
        return actualPlanPredict;
    }

    public void setActualPlanPredict(String actualPlanPredict) {
        this.actualPlanPredict = actualPlanPredict;
    }
    @Column(name="ProjectID")
    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }
    @Column(name="ProjectPathName")
    public String getProjectPathName() {
        return projectPathName;
    }

    public void setProjectPathName(String projectPathName) {
        this.projectPathName = projectPathName;
    }
    @Column(name="ProjectRootID")
    public String getProjectRootID() {
        return projectRootID;
    }

    public void setProjectRootID(String projectRootID) {
        this.projectRootID = projectRootID;
    }
    @Column(name="ProjectLevel")
    public String getProjectLevel() {
        return projectLevel;
    }

    public void setProjectLevel(String projectLevel) {
        this.projectLevel = projectLevel;
    }
    @Column(name="DepartmentID")
    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }
    @Column(name="DepartmentPathName")
    public String getDepartmentPathName() {
        return departmentPathName;
    }

    public void setDepartmentPathName(String departmentPathName) {
        this.departmentPathName = departmentPathName;
    }
    @Column(name="DepartmentRootID")
    public String getDepartmentRootID() {
        return departmentRootID;
    }

    public void setDepartmentRootID(String departmentRootID) {
        this.departmentRootID = departmentRootID;
    }
    @Column(name="ProcessID")
    public String getProcessID() {
        return processID;
    }

    public void setProcessID(String processID) {
        this.processID = processID;
    }
    @Column(name="ProcessName")
    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }
    @Column(name="SumCount")
    public float getSumCount() {
        return sumCount;
    }

    public void setSumCount(float sumCount) {
        this.sumCount = sumCount;
    }
    @Column(name="SumWeight")
    public float getSumWeight() {
        return sumWeight;
    }

    public void setSumWeight(float sumWeight) {
        this.sumWeight = sumWeight;
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
    @Column(name="CreateUserID")
    public String getCreateUserID() {
        return createUserID;
    }

    public void setCreateUserID(String createUserID) {
        this.createUserID = createUserID;
    }
    @Column(name="CreateDate")
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

        SummaryTotal that = (SummaryTotal) o;

        if (Float.compare(that.sumCount, sumCount) != 0) return false;
        if (Float.compare(that.sumWeight, sumWeight) != 0) return false;
        if (Float.compare(that.sumRight, sumRight) != 0) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (curDate != null ? !curDate.equals(that.curDate) : that.curDate != null) return false;
        if (itemName != null ? !itemName.equals(that.itemName) : that.itemName != null) return false;
        if (itemID != null ? !itemID.equals(that.itemID) : that.itemID != null) return false;
        if (itemStyle != null ? !itemStyle.equals(that.itemStyle) : that.itemStyle != null) return false;
        if (startOrEnd != null ? !startOrEnd.equals(that.startOrEnd) : that.startOrEnd != null) return false;
        if (actualPlanPredict != null ? !actualPlanPredict.equals(that.actualPlanPredict) : that.actualPlanPredict != null)
            return false;
        if (projectID != null ? !projectID.equals(that.projectID) : that.projectID != null) return false;
        if (projectPathName != null ? !projectPathName.equals(that.projectPathName) : that.projectPathName != null)
            return false;
        if (projectRootID != null ? !projectRootID.equals(that.projectRootID) : that.projectRootID != null)
            return false;
        if (projectLevel != null ? !projectLevel.equals(that.projectLevel) : that.projectLevel != null) return false;
        if (departmentID != null ? !departmentID.equals(that.departmentID) : that.departmentID != null) return false;
        if (departmentPathName != null ? !departmentPathName.equals(that.departmentPathName) : that.departmentPathName != null)
            return false;
        if (departmentRootID != null ? !departmentRootID.equals(that.departmentRootID) : that.departmentRootID != null)
            return false;
        if (processID != null ? !processID.equals(that.processID) : that.processID != null) return false;
        if (processName != null ? !processName.equals(that.processName) : that.processName != null) return false;
        if (sumDate != null ? !sumDate.equals(that.sumDate) : that.sumDate != null) return false;
        if (sumWeek != null ? !sumWeek.equals(that.sumWeek) : that.sumWeek != null) return false;
        if (sumMonth != null ? !sumMonth.equals(that.sumMonth) : that.sumMonth != null) return false;
        if (sumYear != null ? !sumYear.equals(that.sumYear) : that.sumYear != null) return false;
        if (createUserID != null ? !createUserID.equals(that.createUserID) : that.createUserID != null) return false;
        return !(createDate != null ? !createDate.equals(that.createDate) : that.createDate != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (curDate != null ? curDate.hashCode() : 0);
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        result = 31 * result + (itemID != null ? itemID.hashCode() : 0);
        result = 31 * result + (itemStyle != null ? itemStyle.hashCode() : 0);
        result = 31 * result + (startOrEnd != null ? startOrEnd.hashCode() : 0);
        result = 31 * result + (actualPlanPredict != null ? actualPlanPredict.hashCode() : 0);
        result = 31 * result + (projectID != null ? projectID.hashCode() : 0);
        result = 31 * result + (projectPathName != null ? projectPathName.hashCode() : 0);
        result = 31 * result + (projectRootID != null ? projectRootID.hashCode() : 0);
        result = 31 * result + (projectLevel != null ? projectLevel.hashCode() : 0);
        result = 31 * result + (departmentID != null ? departmentID.hashCode() : 0);
        result = 31 * result + (departmentPathName != null ? departmentPathName.hashCode() : 0);
        result = 31 * result + (departmentRootID != null ? departmentRootID.hashCode() : 0);
        result = 31 * result + (processID != null ? processID.hashCode() : 0);
        result = 31 * result + (processName != null ? processName.hashCode() : 0);
        result = 31 * result + (sumCount != +0.0f ? Float.floatToIntBits(sumCount) : 0);
        result = 31 * result + (sumWeight != +0.0f ? Float.floatToIntBits(sumWeight) : 0);
        result = 31 * result + (sumRight != +0.0f ? Float.floatToIntBits(sumRight) : 0);
        result = 31 * result + (sumDate != null ? sumDate.hashCode() : 0);
        result = 31 * result + (sumWeek != null ? sumWeek.hashCode() : 0);
        result = 31 * result + (sumMonth != null ? sumMonth.hashCode() : 0);
        result = 31 * result + (sumYear != null ? sumYear.hashCode() : 0);
        result = 31 * result + (createUserID != null ? createUserID.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SummaryTotal{" +
                "id=" + id +
                ", curDate=" + curDate +
                ", itemName='" + itemName + '\'' +
                ", itemID='" + itemID + '\'' +
                ", itemStyle='" + itemStyle + '\'' +
                ", startOrEnd='" + startOrEnd + '\'' +
                ", actualPlanPredict='" + actualPlanPredict + '\'' +
                ", projectID='" + projectID + '\'' +
                ", projectPathName='" + projectPathName + '\'' +
                ", projectRootID='" + projectRootID + '\'' +
                ", projectLevel='" + projectLevel + '\'' +
                ", departmentID='" + departmentID + '\'' +
                ", departmentPathName='" + departmentPathName + '\'' +
                ", departmentRootID='" + departmentRootID + '\'' +
                ", processID='" + processID + '\'' +
                ", processName='" + processName + '\'' +
                ", sumCount=" + sumCount +
                ", sumWeight=" + sumWeight +
                ", sumRight=" + sumRight +
                ", sumDate='" + sumDate + '\'' +
                ", sumWeek='" + sumWeek + '\'' +
                ", sumMonth='" + sumMonth + '\'' +
                ", sumYear='" + sumYear + '\'' +
                ", createUserID='" + createUserID + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
