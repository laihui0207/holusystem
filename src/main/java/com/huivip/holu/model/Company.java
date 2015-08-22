package com.huivip.holu.model;

import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by sunlaihui on 6/15/15.
 */
@Entity
@Table(name="R_Company")
@Indexed
@XmlRootElement
public class Company extends BaseObject implements Serializable {
    Long id;
    String companyId;
    String companyFullName;
    String companyShortNameCN;
    String companyShortNameEN;
    String companyCode;
    String companyAddress;
    String companyTel;
    String companyFax;
    String companyMaster;
    String companyNature;
    String companyPositionGPS;
    String companyWebSite;
    String companyNote;
    String companyEmail;
    boolean acceptCreate;
    String createrName;
    String createPassword;
    Date createDate;
    String createMobile;
    String createLoginCode;
    String companyStockCode;


   /* Set<Project> projectSet=new HashSet<>();*/

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
    @Column(name = "CompanyID")
    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    @Column(name="CompanyFullName")
    public String getCompanyFullName() {
        return companyFullName;
    }

    public void setCompanyFullName(String companyFullName) {
        this.companyFullName = companyFullName;
    }
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    @Column(name="CompanyShortNameCN")
    public String getCompanyShortNameCN() {
        return companyShortNameCN;
    }

    public void setCompanyShortNameCN(String companyShortNameCN) {
        this.companyShortNameCN = companyShortNameCN;
    }
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    @Column(name="CompanyShortNameEN")
    public String getCompanyShortNameEN() {
        return companyShortNameEN;
    }

    public void setCompanyShortNameEN(String companyShortNameEN) {
        this.companyShortNameEN = companyShortNameEN;
    }
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    @Column(name="CompanyCode")
    public String getCompanyCode() {
        return companyCode;
    }
    @Column(name="CompanyCode")
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    @Column(name="CompanyAddress")
    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String comapnyAddress) {
        this.companyAddress = comapnyAddress;
    }
    @Column(name="CompanyTel")
    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }
    @Column(name="CompanyFax")
    public String getCompanyFax() {
        return companyFax;
    }

    public void setCompanyFax(String companyFax) {
        this.companyFax = companyFax;
    }
    @Column(name="CompanyMaster")
    public String getCompanyMaster() {
        return companyMaster;
    }

    public void setCompanyMaster(String companyMaster) {
        this.companyMaster = companyMaster;
    }

    @Column(name="CompanyNature")
    public String getCompanyNature() {
        return companyNature;
    }
    public void setCompanyNature(String companyNature) {
        this.companyNature = companyNature;
    }
    @Column(name="CompanyPositionGPS")
    public String getCompanyPositionGPS() {
        return companyPositionGPS;
    }

    public void setCompanyPositionGPS(String companyPositionGPS) {
        this.companyPositionGPS = companyPositionGPS;
    }
    @Column(name="CompanyWebSite")
    public String getCompanyWebSite() {
        return companyWebSite;
    }

    public void setCompanyWebSite(String companyWebSite) {
        this.companyWebSite = companyWebSite;
    }
    @Column(name="CompanyNote")
    public String getCompanyNote() {
        return companyNote;
    }

    public void setCompanyNote(String companyNote) {
        this.companyNote = companyNote;
    }
    @Column(name="CompanyEmail")
    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }
    @Column(name="AcceptCreate")
    public boolean isAcceptCreate() {
        return acceptCreate;
    }

    public void setAcceptCreate(boolean acceptCreate) {
        this.acceptCreate = acceptCreate;
    }
    @Column(name="CreaterName")
    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }
    @Column(name="CreatePassword")
    public String getCreatePassword() {
        return createPassword;
    }

    public void setCreatePassword(String createPassword) {
        this.createPassword = createPassword;
    }
    @Column(name="CreateDate")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /*@OneToMany(mappedBy = "company")
        @JsonIgnore
        public Set<Project> getProjectSet() {
            return projectSet;
        }

        public void setProjectSet(Set<Project> projectSet) {
            this.projectSet = projectSet;
        }
    */
    @Column(name="CreateMobile")
    public String getCreateMobile() {
        return createMobile;
    }

    public void setCreateMobile(String createMobile) {
        this.createMobile = createMobile;
    }
    @Column(name="CreateLoginCode")
    public String getCreateLoginCode() {
        return createLoginCode;
    }

    public void setCreateLoginCode(String createLoginCode) {
        this.createLoginCode = createLoginCode;
    }
    @Column(name="CompanyStockCode")
    public String getCompanyStockCode() {
        return companyStockCode;
    }

    public void setCompanyStockCode(String companyStockCode) {
        this.companyStockCode = companyStockCode;
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
        if (companyAddress != null ? !companyAddress.equals(company.companyAddress) : company.companyAddress != null)
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
        result = 31 * result + (companyAddress != null ? companyAddress.hashCode() : 0);
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
                ", comapnyAddress='" + companyAddress + '\'' +
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
