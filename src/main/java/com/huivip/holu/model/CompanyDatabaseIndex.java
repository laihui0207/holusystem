package com.huivip.holu.model;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by sunlaihui on 7/28/15.
 */
@Entity
@Table(name = "R_CompanyDatabaseIndex")
@Indexed
@XmlRootElement
public class CompanyDatabaseIndex extends BaseObject implements Serializable {
    Long id;
    String tableStyle;
    String tableName;
    Company company;
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
    @Column(name="TableStyle")
    public String getTableStyle() {
        return tableStyle;
    }

    public void setTableStyle(String tableStyle) {
        this.tableStyle = tableStyle;
    }
    @Column(name="TableName")
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    @ManyToOne
    @JoinColumn(name="CompanyID",referencedColumnName = "companyID")
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

        CompanyDatabaseIndex that = (CompanyDatabaseIndex) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (tableStyle != null ? !tableStyle.equals(that.tableStyle) : that.tableStyle != null) return false;
        if (tableName != null ? !tableName.equals(that.tableName) : that.tableName != null) return false;
        return !(company != null ? !company.equals(that.company) : that.company != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (tableStyle != null ? tableStyle.hashCode() : 0);
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CompanyDatabaseIndex{" +
                "id=" + id +
                ", tableStyle='" + tableStyle + '\'' +
                ", tableName='" + tableName + '\'' +
                ", company=" + company +
                '}';
    }
}
