package com.huivip.holu.service;

import com.huivip.holu.model.Setting;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface SettingManager extends GenericManager<Setting, Long> {

    List<Setting> getSettingByCompany(String companyID);
    Setting getSettingBySearch(String companyID,String keyArea,String keyName);
}