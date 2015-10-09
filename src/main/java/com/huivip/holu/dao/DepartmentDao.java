package com.huivip.holu.dao;

import com.huivip.holu.model.Department;

import java.util.List;

/**
 * An interface that provides a data management interface to the Department table.
 */
public interface DepartmentDao extends GenericDao<Department, Long> {
    Department getDepartmentByDepartmentID(String departmentID);
    List<Department> getDepartmentByCompany(String companyID);


}