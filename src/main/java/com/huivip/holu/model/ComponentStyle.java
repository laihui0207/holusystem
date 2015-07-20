package com.huivip.holu.model;

import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by sunlaihui on 6/22/15.
 * <p/>
 * SELECT TOP 1000 [ID]
 * ,[StyleName]
 * ,[ProcessName]
 * ,[ProcessOrder]
 * ,[CompanyID]
 * FROM [MidDatabase].[dbo].[R_ComponentStyle]
 */

@Entity
@Table(name = "R_ComponentStyle")
@Indexed
@XmlRootElement
public class ComponentStyle extends BaseObject implements Serializable {
    Long id;
    String styleName;
    String styleProcessID;
    String processID;
    String styleID;
    String processName;
    int processOrder;
    String processRight;
    String processProduction;
    String processNote;
    Company company;
    boolean operationer=false;

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
    public int getProcessOrder() {
        return processOrder;
    }

    public void setProcessOrder(int processOrder) {
        this.processOrder = processOrder;
    }

    @ManyToOne
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
    @Column(name="ProcessID")
    public String getProcessID() {
        return processID;
    }

    public void setProcessID(String processID) {
        this.processID = processID;
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
