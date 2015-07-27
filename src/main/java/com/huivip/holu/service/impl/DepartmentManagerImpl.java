package com.huivip.holu.service.impl;

import com.huivip.holu.dao.DepartmentDao;
import com.huivip.holu.model.Department;
import com.huivip.holu.service.DepartmentManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}