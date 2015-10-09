package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.DepartmentDao;
import com.huivip.holu.model.Department;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("departmentDao")
public class DepartmentDaoHibernate extends GenericDaoHibernate<Department, Long> implements DepartmentDao {

    public DepartmentDaoHibernate() {
        super(Department.class);
    }

    @Override
    public Department getDepartmentByDepartmentID(String departmentID) {
        String hsqlString="From Department where departmentID='"+departmentID+"'";
        Query query=getSession().createQuery(hsqlString);
        List<Department> list=query.list();
        if(null==list || list.size()==0){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Department> getDepartmentByCompany(String companyID) {
        String hsqlString="From Department where company.companyId='"+companyID+"'";
        Query query=getSession().createQuery(hsqlString);
        List<Department> list=query.list();
        return list;
    }
}
