package com.diploma.demo.core.landplot.service.impl;

import com.diploma.demo.core.landplot.LandPlot;
import com.diploma.demo.core.landplot.repository.LandPlotRepository;
import com.diploma.demo.core.landplot.service.LandPlotService;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class LandPlotServiceImpl implements LandPlotService {
    @Autowired
    LandPlotRepository landPlotRepository;

    @Autowired
    AuditReader auditReader;

    @Override
    public LandPlot addLandPlot(LandPlot landPlot) {
        LandPlot savedLandPlot = landPlotRepository.saveAndFlush(landPlot);
        return savedLandPlot;
    }

    public Optional<LandPlot>  getById(Long id) {
        return landPlotRepository.findById(id);
    }

    public void updateLandPlot(LandPlot landPlot) {
        landPlotRepository.saveAndFlush(landPlot);
    }

    public List<LandPlot> getAll() {
        return landPlotRepository.findAll();
    }

    public List getRevisions(Long id) {
        AuditQuery auditQuery = null;
        auditQuery = auditReader.createQuery()
                .forRevisionsOfEntity(LandPlot.class, false,true);

        auditQuery.add(AuditEntity.id().eq(id));

        return auditQuery.getResultList();
    }
}