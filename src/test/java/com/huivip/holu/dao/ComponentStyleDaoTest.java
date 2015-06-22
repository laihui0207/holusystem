package com.huivip.holu.dao;

import com.huivip.holu.dao.BaseDaoTestCase;
import com.huivip.holu.model.ComponentStyle;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ComponentStyleDaoTest extends BaseDaoTestCase {
    @Autowired
    private ComponentStyleDao componentStyleDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveComponentStyle() {
        ComponentStyle componentStyle = new ComponentStyle();

        // enter all required fields
        componentStyle.setProcessOrder(2090312916);

        log.debug("adding componentStyle...");
        componentStyle = componentStyleDao.save(componentStyle);

        componentStyle = componentStyleDao.get(componentStyle.getId());

        assertNotNull(componentStyle.getId());

        log.debug("removing componentStyle...");

        componentStyleDao.remove(componentStyle.getId());

        // should throw DataAccessException 
        componentStyleDao.get(componentStyle.getId());
    }
}