package com.huivip.holu.service;

import com.huivip.holu.model.Department;

import javax.jws.WebService;

@WebService
public interface DepartmentManager extends GenericManager<Department, Long> {

    Department getDepartmentByDepartmentID(String departmentID);
    
}