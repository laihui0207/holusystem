package com.huivip.holu.dao;

import com.huivip.holu.dao.BaseDaoTestCase;
import com.huivip.holu.model.Component;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ComponentDaoTest extends BaseDaoTestCase {
    @Autowired
    private ComponentDao componentDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveComponent() {
        Component component = new Component();

        // enter all required fields
        component.setLength(new Float(1.6687563E38));
        component.setPrice(new Float(1.5431204E38));
        component.setSize(new Float(1.4052927E36));
        component.setWeight(new Float(2.5606646E38));

        log.debug("adding component...");
        component = componentDao.save(component);

        component = componentDao.get(component.getId());

        assertNotNull(component.getId());

        log.debug("removing component...");

        componentDao.remove(component.getId());

        // should throw DataAccessException 
        componentDao.get(component.getId());
    }
}