package com.huivip.holu.service.impl;

import com.huivip.holu.dao.UserGroupDao;
import com.huivip.holu.model.SelectLabelValue;
import com.huivip.holu.model.UserGroup;
import com.huivip.holu.service.UserGroupManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;

@Service("userGroupManager")
@WebService(serviceName = "UserGroupService", endpointInterface = "com.huivip.holu.service.UserGroupManager")
public class UserGroupManagerImpl extends GenericManagerImpl<UserGroup, Long> implements UserGroupManager {
    UserGroupDao userGroupDao;

    @Autowired
    public UserGroupManagerImpl(UserGroupDao userGroupDao) {
        super(userGroupDao);
        this.userGroupDao = userGroupDao;
    }

    @Override
    public List<UserGroup> userGroups() {
        return getAll();
    }

    @Override
    public List<SelectLabelValue> listUserGroupSLV() {
        List<UserGroup> list=userGroups();
        List<SelectLabelValue> result=new ArrayList<>();
        for(UserGroup ug:list){
            SelectLabelValue slv=new SelectLabelValue();
            slv.setIcon(null);
            slv.setId(ug.getId().toString());
            slv.setText(ug.getName());
            slv.setChecked(false);
            result.add(slv);
        }
        return result;
    }
}