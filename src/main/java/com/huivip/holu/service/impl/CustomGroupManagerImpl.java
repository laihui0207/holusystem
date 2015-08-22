package com.huivip.holu.service.impl;

import com.huivip.holu.dao.CustomGroupDao;
import com.huivip.holu.model.CustomGroup;
import com.huivip.holu.model.SelectLabelValue;
import com.huivip.holu.service.CustomGroupManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;

@Service("customGroupManager")
@WebService(serviceName = "CustomGroupService", endpointInterface = "com.huivip.holu.service.CustomGroupManager")
public class CustomGroupManagerImpl extends GenericManagerImpl<CustomGroup, Long> implements CustomGroupManager {
    CustomGroupDao customGroupDao;

    @Autowired
    public CustomGroupManagerImpl(CustomGroupDao customGroupDao) {
        super(customGroupDao);
        this.customGroupDao = customGroupDao;
    }

    @Override
    public List<SelectLabelValue> getGroups() {
        List<SelectLabelValue> result=new ArrayList<>();
        List<CustomGroup> list=customGroupDao.getAll();
        for(CustomGroup group:list){
            SelectLabelValue slv=new SelectLabelValue();
            slv.setText(group.getName());
            slv.setIcon(null);
            slv.setId(group.getGroupID());
            slv.setChecked(false);
            result.add(slv);
        }
        return result;
    }

    @Override
    public CustomGroup getCustomGroupByGroupId(String groupID) {
        return customGroupDao.getGroupByGroupID(groupID);
    }
}