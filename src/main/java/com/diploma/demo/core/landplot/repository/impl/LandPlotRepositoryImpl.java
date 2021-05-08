package com.diploma.demo.core.landplot.repository.impl;

import com.diploma.demo.core.landplot.repository.LandPlotRepositoryCustom;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class LandPlotRepositoryImpl implements LandPlotRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public EntityManager getEm() {
        return this.em;
    }
}
