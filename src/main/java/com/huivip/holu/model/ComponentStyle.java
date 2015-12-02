package com.huivip.holu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by sunlaihui on 6/22/15.
 * <p/>
 *CREATE TABLE MidDatabase.dbo.R_ComponentStyle (
 ID int NOT NULL,
 StyleProcessID nvarchar(50),
 StyleID nvarchar(50),
 StyleName nvarchar(50),
 ProcessID nvarchar(50),
 ProcessName nvarchar(50),
 ProcessOrder int,
 ProcessRight float,
 ProcessProduction float,
 ProcessStep float,
 ProcessNote nvarchar(500),
 CompanyID nvarchar(50)
 );
 */

@Entity
@Table(name = "R_ComponentStyle")
@Indexed
@XmlRootElement
public class ComponentStyle extends BaseObject implements Serializable {
    Long id;
    String styleName;
    String styleProcessID;
/*    String processID;*/
    ProcessDictionary processDictionary;
    String styleID;
    String processName;
    Integer processOrder;
    Integer processStep;
    String processRight;
    String processProduction;
    String processNote;
    Company company;
    boolean operationer=false;
    Date confirmDate;
    User confirmer;
    ProcessMid processMid;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DocumentId
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    @Column(name = "StyleName")
    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }
    @Column(name = "StyleID")
    public String getStyleID() {
        return styleID;
    }

    public void setStyleID(String styleID) {
        this.styleID = styleID;
    }

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    @Column(name="ProcessName")
    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }
    @Column(name="ProcessOrder")
    public Integer getProcessOrder() {
        return processOrder;
    }
    @Column(name="ProcessStep")
/*    @Transient*/
    public Integer getProcessStep() {
        return processStep;
    }

    public void setProcessStep(Integer processStep) {
        this.processStep = processStep;
    }

    public void setProcessOrder(Integer processOrder) {
        this.processOrder = processOrder;
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "CompanyID",referencedColumnName = "companyID")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    @Column(name="ProcessRight")
    public String getProcessRight() {
        return processRight;
    }

    public void setProcessRight(String processRight) {
        this.processRight = processRight;
    }
    @Column(name="ProcessProduction")
    public String getProcessProduction() {
        return processProduction;
    }

    public void setProcessProduction(String processProduction) {
        this.processProduction = processProduction;
    }
    @Column(name="ProcessNote")
    public String getProcessNote() {
        return processNote;
    }

    public void setProcessNote(String processNote) {
        this.processNote = processNote;
    }
    @Transient
    public boolean isOperationer() {
        return operationer;
    }

    public void setOperationer(boolean operationer) {
        this.operationer = operationer;
    }
    @Column(name="StyleProcessID")
    public String getStyleProcessID() {
        return styleProcessID;
    }

    public void setStyleProcessID(String styleProcessID) {
        this.styleProcessID = styleProcessID;
    }
    /*@Column(name="ProcessID")
    public String getProcessID() {
        return processID;
    }

    public void setProcessID(String processID) {
        this.processID = processID;
    }*/
    @ManyToOne
    @JoinColumn(name="ProcessID",referencedColumnName = "processID")
    public ProcessDictionary getProcessDictionary() {
        return processDictionary;
    }

    public void setProcessDictionary(ProcessDictionary processDictionary) {
        this.processDictionary = processDictionary;
    }
    @Transient
    @JsonIgnore
    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }
    @Transient
    @JsonIgnore
    public User getConfirmer() {
        return confirmer;
    }

    public void setConfirmer(User confirmer) {
        this.confirmer = confirmer;
    }
    @Transient
    public ProcessMid getProcessMid() {
        return processMid;
    }

    public void setProcessMid(ProcessMid processMid) {
        this.processMid = processMid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComponentStyle that = (ComponentStyle) o;

        if (processOrder != that.processOrder) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (styleName != null ? !styleName.equals(that.styleName) : that.styleName != null) return false;
        if (styleID != null ? !styleID.equals(that.styleID) : that.styleID != null) return false;
        if (processName != null ? !processName.equals(that.processName) : that.processName != null) return false;
        if (processRight != null ? !processRight.equals(that.processRight) : that.processRight != null) return false;
        if (processProduction != null ? !processProduction.equals(that.processProduction) : that.processProduction != null)
            return false;
        if (processNote != null ? !processNote.equals(that.processNote) : that.processNote != null) return false;
        return !(company != null ? !company.equals(that.company) : that.company != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (styleName != null ? styleName.hashCode() : 0);
        result = 31 * result + (styleID != null ? styleID.hashCode() : 0);
        result = 31 * result + (processName != null ? processName.hashCode() : 0);
        result = 31 * result + processOrder;
        result = 31 * result + (processRight != null ? processRight.hashCode() : 0);
        result = 31 * result + (processProduction != null ? processProduction.hashCode() : 0);
        result = 31 * result + (processNote != null ? processNote.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ComponentStyle{" +
                "id=" + id +
                ", styleName='" + styleName + '\'' +
                ", styleID='" + styleID + '\'' +
                ", processName='" + processName + '\'' +
                ", processOrder=" + processOrder +
                ", processRight='" + processRight + '\'' +
                ", processProduction='" + processProduction + '\'' +
                ", processNote='" + processNote + '\'' +
                ", company=" + company +
                '}';
    }
}
