package com.huivip.holu.model;

import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sunlaihui on 6/15/15.
 */
@Entity
@Table(name="R_company")
@Indexed
@XmlRootElement
public class Company extends BaseObject implements Serializable {
    Long id;
    String companyId;
    String companyFullName;
    String companyShortNameCN;
    String companyShortNameEN;
    String companyCode;
    String comapnyAddress;
    String companyTel;
    String companyFax;
    String companyMaster;
    String companyNature;
    String companyPositionGPS;
    String companyWebSite;
    String companyNote;

    Set<Project> projectSet=new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DocumentId
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    public String getCompanyFullName() {
        return companyFullName;
    }

    public void setCompanyFullName(String companyFullName) {
        this.companyFullName = companyFullName;
    }
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    public String getCompanyShortNameCN() {
        return companyShortNameCN;
    }

    public void setCompanyShortNameCN(String companyShortNameCN) {
        this.companyShortNameCN = companyShortNameCN;
    }
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    public String getCompanyShortNameEN() {
        return companyShortNameEN;
    }

    public void setCompanyShortNameEN(String companyShortNameEN) {
        this.companyShortNameEN = companyShortNameEN;
    }
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getComapnyAddress() {
        return comapnyAddress;
    }

    public void setComapnyAddress(String comapnyAddress) {
        this.comapnyAddress = comapnyAddress;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    public String getCompanyFax() {
        return companyFax;
    }

    public void setCompanyFax(String companyFax) {
        this.companyFax = companyFax;
    }

    public String getCompanyMaster() {
        return companyMaster;
    }

    public void setCompanyMaster(String companyMaster) {
        this.companyMaster = companyMaster;
    }

    public String getCompanyNature() {
        return companyNature;
    }

    public void setCompanyNature(String companyNature) {
        this.companyNature = companyNature;
    }

    public String getCompanyPositionGPS() {
        return companyPositionGPS;
    }

    public void setCompanyPositionGPS(String companyPositionGPS) {
        this.companyPositionGPS = companyPositionGPS;
    }

    public String getCompanyWebSite() {
        return companyWebSite;
    }

    public void setCompanyWebSite(String companyWebSite) {
        this.companyWebSite = companyWebSite;
    }

    public String getCompanyNote() {
        return companyNote;
    }

    public void setCompanyNote(String companyNote) {
        this.companyNote = companyNote;
    }
    @OneToMany(mappedBy = "company")
    public Set<Project> getProjectSet() {
        return projectSet;
    }

    public void setProjectSet(Set<Project> projectSet) {
        this.projectSet = projectSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (id != null ? !id.equals(company.id) : company.id != null) return false;
        if (companyId != null ? !companyId.equals(company.companyId) : company.companyId != null) return false;
        if (companyFullName != null ? !companyFullName.equals(company.companyFullName) : company.companyFullName != null)
            return false;
        if (companyShortNameCN != null ? !companyShortNameCN.equals(company.companyShortNameCN) : company.companyShortNameCN != null)
            return false;
        if (companyShortNameEN != null ? !companyShortNameEN.equals(company.companyShortNameEN) : company.companyShortNameEN != null)
            return false;
        if (companyCode != null ? !companyCode.equals(company.companyCode) : company.companyCode != null) return false;
        if (comapnyAddress != null ? !comapnyAddress.equals(company.comapnyAddress) : company.comapnyAddress != null)
            return false;
        if (companyTel != null ? !companyTel.equals(company.companyTel) : company.companyTel != null) return false;
        if (companyFax != null ? !companyFax.equals(company.companyFax) : company.companyFax != null) return false;
        if (companyMaster != null ? !companyMaster.equals(company.companyMaster) : company.companyMaster != null)
            return false;
        if (companyNature != null ? !companyNature.equals(company.companyNature) : company.companyNature != null)
            return false;
        if (companyPositionGPS != null ? !companyPositionGPS.equals(company.companyPositionGPS) : company.companyPositionGPS != null)
            return false;
        if (companyWebSite != null ? !companyWebSite.equals(company.companyWebSite) : company.companyWebSite != null)
            return false;
        return !(companyNote != null ? !companyNote.equals(company.companyNote) : company.companyNote != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
        result = 31 * result + (companyFullName != null ? companyFullName.hashCode() : 0);
        result = 31 * result + (companyShortNameCN != null ? companyShortNameCN.hashCode() : 0);
        result = 31 * result + (companyShortNameEN != null ? companyShortNameEN.hashCode() : 0);
        result = 31 * result + (companyCode != null ? companyCode.hashCode() : 0);
        result = 31 * result + (comapnyAddress != null ? comapnyAddress.hashCode() : 0);
        result = 31 * result + (companyTel != null ? companyTel.hashCode() : 0);
        result = 31 * result + (companyFax != null ? companyFax.hashCode() : 0);
        result = 31 * result + (companyMaster != null ? companyMaster.hashCode() : 0);
        result = 31 * result + (companyNature != null ? companyNature.hashCode() : 0);
        result = 31 * result + (companyPositionGPS != null ? companyPositionGPS.hashCode() : 0);
        result = 31 * result + (companyWebSite != null ? companyWebSite.hashCode() : 0);
        result = 31 * result + (companyNote != null ? companyNote.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", companyId='" + companyId + '\'' +
                ", companyFullName='" + companyFullName + '\'' +
                ", companyShortNameCN='" + companyShortNameCN + '\'' +
                ", companyShortNameEN='" + companyShortNameEN + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", comapnyAddress='" + comapnyAddress + '\'' +
                ", companyTel='" + companyTel + '\'' +
                ", companyFax='" + companyFax + '\'' +
                ", companyMaster='" + companyMaster + '\'' +
                ", companyNature='" + companyNature + '\'' +
                ", companyPositionGPS='" + companyPositionGPS + '\'' +
                ", companyWebSite='" + companyWebSite + '\'' +
                ", companyNote='" + companyNote + '\'' +
                '}';
    }
}
