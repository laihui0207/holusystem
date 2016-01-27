package com.huivip.holu.service.impl;

import com.huivip.holu.dao.CompanyDao;
import com.huivip.holu.model.Company;
import com.huivip.holu.service.CompanyManager;

import com.huivip.holu.util.cache.Cache2kProvider;
import org.cache2k.Cache;
import org.cache2k.CacheBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("companyManager")
@WebService(serviceName = "CompanyService", endpointInterface = "com.huivip.holu.service.CompanyManager")
public class CompanyManagerImpl extends GenericManagerImpl<Company, Long> implements CompanyManager {
    CompanyDao companyDao;
    Cache<String,Company> cache= null;
    Cache<String,List<Company>> listCache=Cache2kProvider.getinstance().getListCache();

    @Autowired
    public CompanyManagerImpl(CompanyDao companyDao) {
        super(companyDao);
        this.companyDao = companyDao;
        cache=Cache2kProvider.getinstance().setCache(Company.class, CacheBuilder.newCache(String.class,Company.class).build());
    }

    @Override
    public Company getCompanyByCompanyID(String companyID) {
        Company company=cache.peek(companyID);
        if(company==null){
            company=companyDao.getCompanyByCompanyID(companyID);
            cache.put(companyID,company);
        }
        return company;
    }

    @Override
    public List<Company> companyList() {
        List<Company> list=listCache.peek(Company.LIST_CACHE_KEY);
        if(list==null){
            list=getAll();
            listCache.put(Company.LIST_CACHE_KEY,list);
        }
        return list;
    }

    @Override
    public boolean isValidCompanyNote(String note) {
        return companyDao.isValidCompanyNote(note);
    }

    @Override
    public Company getCompanyFromNote(String note) {
        return companyDao.getCompanyFromNote(note);
    }

    @Override
    public List<Company> getAll() {
        List<Company> list=listCache.peek(Company.LIST_CACHE_KEY);
        if(list==null){
            list=super.getAll();
            listCache.put(Company.LIST_CACHE_KEY,list);
        }
        return list;
    }

    @Override
    public Company get(Long id) {
        Company company=cache.peek(id.toString());
        if(company==null){
            company=super.get(id);
            cache.put(id.toString(),company);
        }
        return company;
    }
}