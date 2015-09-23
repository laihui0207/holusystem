package com.huivip.holu.dao;

import com.huivip.holu.model.Setting;

import java.util.List;

/**
 * An interface that provides a data management interface to the Setting table.
 */
public interface SettingDao extends GenericDao<Setting, Long> {
    List<Setting> getSettingByCompany(String companyID);
    Setting getSettingBySearch(String companyID,String keyArea,String keyName);
}