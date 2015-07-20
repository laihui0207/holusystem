package com.huivip.holu.dao.hibernate;

import com.huivip.holu.model.Department;
import com.huivip.holu.dao.DepartmentDao;
import com.huivip.holu.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("departmentDao")
public class DepartmentDaoHibernate extends GenericDaoHibernate<Department, Long> implements DepartmentDao {

    public DepartmentDaoHibernate() {
        super(Department.class);
    }
}
