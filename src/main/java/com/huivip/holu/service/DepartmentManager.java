package com.huivip.holu.service;

import com.huivip.holu.service.GenericManager;
import com.huivip.holu.model.Department;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface DepartmentManager extends GenericManager<Department, Long> {
    
}