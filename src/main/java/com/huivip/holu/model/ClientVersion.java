package com.huivip.holu.model;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by sunlaihui on 9/8/15.
 */
@Entity
@Table(name="ClientVersion")
@Indexed
@XmlRootElement
public class ClientVersion {
    private Long id;
    private String version;
    private String releaseNote;
    private String QRCode;
    private String clientType;
    private String clientUrl;
    private String storePath;
    private Date createTime=new Date();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(unique = true)
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getReleaseNote() {
        return releaseNote;
    }

    public void setReleaseNote(String releaseNote) {
        this.releaseNote = releaseNote;
    }

    public String getQRCode() {
        return QRCode;
    }

    public void setQRCode(String QRCode) {
        this.QRCode = QRCode;
    }

    public String getStorePath() {
        return storePath;
    }

    public void setStorePath(String storePath) {
        this.storePath = storePath;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getClientUrl() {
        return clientUrl;
    }

    public void setClientUrl(String clientUrl) {
        this.clientUrl = clientUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientVersion that = (ClientVersion) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;
        if (releaseNote != null ? !releaseNote.equals(that.releaseNote) : that.releaseNote != null) return false;
        if (QRCode != null ? !QRCode.equals(that.QRCode) : that.QRCode != null) return false;
        if (clientType != null ? !clientType.equals(that.clientType) : that.clientType != null) return false;
        if (clientUrl != null ? !clientUrl.equals(that.clientUrl) : that.clientUrl != null) return false;
        if (storePath != null ? !storePath.equals(that.storePath) : that.storePath != null) return false;
        return !(createTime != null ? !createTime.equals(that.createTime) : that.createTime != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (releaseNote != null ? releaseNote.hashCode() : 0);
        result = 31 * result + (QRCode != null ? QRCode.hashCode() : 0);
        result = 31 * result + (clientType != null ? clientType.hashCode() : 0);
        result = 31 * result + (clientUrl != null ? clientUrl.hashCode() : 0);
        result = 31 * result + (storePath != null ? storePath.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClientVersion{" +
                "id=" + id +
                ", version='" + version + '\'' +
                ", releaseNote='" + releaseNote + '\'' +
                ", QRCode='" + QRCode + '\'' +
                ", clientType='" + clientType + '\'' +
                ", clientUrl='" + clientUrl + '\'' +
                ", storePath='" + storePath + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
