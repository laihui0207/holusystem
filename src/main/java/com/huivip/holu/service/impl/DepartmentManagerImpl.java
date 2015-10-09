package com.huivip.holu.service.impl;

import com.huivip.holu.dao.DepartmentDao;
import com.huivip.holu.model.Department;
import com.huivip.holu.model.SelectLabelValue;
import com.huivip.holu.model.User;
import com.huivip.holu.service.DepartmentManager;
import com.huivip.holu.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@Service("departmentManager")
@WebService(serviceName = "DepartmentService", endpointInterface = "com.huivip.holu.service.DepartmentManager")
public class DepartmentManagerImpl extends GenericManagerImpl<Department, Long> implements DepartmentManager {
    DepartmentDao departmentDao;
    @Autowired
    UserManager userManager;

    @Autowired
    public DepartmentManagerImpl(DepartmentDao departmentDao) {
        super(departmentDao);
        this.departmentDao = departmentDao;
    }

    @Override
    public Department getDepartmentByDepartmentID(String departmentID) {
        return departmentDao.getDepartmentByDepartmentID(departmentID);
    }

    @Override
    public List<SelectLabelValue> getDepartmentLabelValue(String userID) {
        List<Department> list;
        if(userID==null || userID.equalsIgnoreCase("") || userID.equalsIgnoreCase("undefined")){
            list=departmentDao.getAll();
        }
        else {
            User user=userManager.getUserByUserID(userID);
            list=departmentDao.getDepartmentByCompany(user.getCompany().getCompanyId());
        }
        List<SelectLabelValue> result=new ArrayList<>();
        for(Department dp: list){
            SelectLabelValue slv=new SelectLabelValue();
            slv.setChecked(false);
            slv.setIcon(null);
            slv.setId(dp.getDepartmentID());
            slv.setText(dp.getPathName());
            result.add(slv);
        }
        return result;
    }
}