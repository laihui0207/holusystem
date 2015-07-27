package com.huivip.holu.dao;

import com.huivip.holu.model.Department;

/**
 * An interface that provides a data management interface to the Department table.
 */
public interface DepartmentDao extends GenericDao<Department, Long> {
    Department getDepartmentByDepartmentID(String departmentID);

}