package com.huivip.holu.service.impl;

import com.huivip.holu.dao.SettingDao;
import com.huivip.holu.model.Setting;
import com.huivip.holu.service.SettingManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("settingManager")
@WebService(serviceName = "SettingService", endpointInterface = "com.huivip.holu.service.SettingManager")
public class SettingManagerImpl extends GenericManagerImpl<Setting, Long> implements SettingManager {
    SettingDao settingDao;

    @Autowired
    public SettingManagerImpl(SettingDao settingDao) {
        super(settingDao);
        this.settingDao = settingDao;
    }

    @Override
    public List<Setting> getSettingByCompany(String companyID) {
        return settingDao.getSettingByCompany(companyID);
    }

    @Override
    public Setting getSettingBySearch(String companyID, String keyArea, String keyName) {
        return settingDao.getSettingBySearch(companyID,keyArea,keyName);
    }
}