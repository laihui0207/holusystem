package com.huivip.holu.service.impl;

import com.huivip.holu.dao.DepartmentDao;
import com.huivip.holu.model.Department;
import com.huivip.holu.model.LabelValue;
import com.huivip.holu.model.SelectLabelValue;
import com.huivip.holu.service.DepartmentManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;

@Service("departmentManager")
@WebService(serviceName = "DepartmentService", endpointInterface = "com.huivip.holu.service.DepartmentManager")
public class DepartmentManagerImpl extends GenericManagerImpl<Department, Long> implements DepartmentManager {
    DepartmentDao departmentDao;

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
    public List<SelectLabelValue> getDepartmentLabelValue() {
        List<Department> list=departmentDao.getAll();
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