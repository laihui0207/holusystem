package com.huivip.holu.model;

import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by sunlaihui on 6/22/15.
 *
 * SELECT TOP 1000 [ID]
 ,[StyleName]
 ,[ProcessName]
 ,[ProcessOrder]
 ,[CompanyID]
 FROM [MidDatabase].[dbo].[R_ComponentStyle]
 */

@Entity
@Table(name = "R_ComponetStyle")
@Indexed
@XmlRootElement
public class ComponentStyle extends BaseObject implements Serializable {
    Long id;
    String styleName;
    String processName;
    int processOrder;
    Company company;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DocumentId
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public int getProcessOrder() {
        return processOrder;
    }

    public void setProcessOrder(int processOrder) {
        this.processOrder = processOrder;
    }
    @ManyToOne
    @JoinColumn(name = "CompanyID")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComponentStyle that = (ComponentStyle) o;

        if (processOrder != that.processOrder) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (styleName != null ? !styleName.equals(that.styleName) : that.styleName != null) return false;
        if (processName != null ? !processName.equals(that.processName) : that.processName != null) return false;
        return !(company != null ? !company.equals(that.company) : that.company != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (styleName != null ? styleName.hashCode() : 0);
        result = 31 * result + (processName != null ? processName.hashCode() : 0);
        result = 31 * result + processOrder;
        result = 31 * result + (company != null ? company.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ComponentStyle{" +
                "id=" + id +
                ", styleName='" + styleName + '\'' +
                ", processName='" + processName + '\'' +
                ", processOrder=" + processOrder +
                ", company=" + company +
                '}';
    }
}
