package com.huivip.holu.dao.hibernate;

import com.huivip.holu.model.ProcessDictionary;
import com.huivip.holu.dao.ProcessDictionaryDao;
import com.huivip.holu.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("processDictionaryDao")
public class ProcessDictionaryDaoHibernate extends GenericDaoHibernate<ProcessDictionary, Long> implements ProcessDictionaryDao {

    public ProcessDictionaryDaoHibernate() {
        super(ProcessDictionary.class);
    }
}
