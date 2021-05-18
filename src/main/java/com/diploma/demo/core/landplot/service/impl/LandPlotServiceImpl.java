package com.diploma.demo.core.landplot.service.impl;

import com.diploma.demo.core.landplot.LandPlot;
import com.diploma.demo.core.landplot.repository.LandPlotRepository;
import com.diploma.demo.core.landplot.service.LandPlotService;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
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
        return landPlotRepository.saveAndFlush(landPlot);
    }

    public Optional<LandPlot> findById(Long id) {
        return landPlotRepository.findById(id);
    }

    public void update(LandPlot landPlot) {
        landPlotRepository.saveAndFlush(landPlot);
    }

    public List<LandPlot> getAll() {
        return landPlotRepository.findAll();
    }

    private long localeDateToTimeStamp(LocalDate date) {
        return date.atStartOfDay(ZoneId.systemDefault()).toInstant().getLong(ChronoField.INSTANT_SECONDS) * 1000;
    }

    public List getRevisions(Long id, LocalDate startDate, LocalDate endDate) {
        AuditQuery auditQuery;
        auditQuery = auditReader.createQuery()
                .forRevisionsOfEntity(LandPlot.class, false,true);

        auditQuery.add(AuditEntity.id().eq(id));

        if (startDate != null) {
            auditQuery.add(AuditEntity.revisionProperty("timestamp").gt(localeDateToTimeStamp(startDate)));
        }

        if (endDate != null) {
            auditQuery.add(AuditEntity.revisionProperty("timestamp").lt(localeDateToTimeStamp(endDate)));
        }

        return auditQuery.getResultList();
    }

    @Override
    public List getAllRevisions() {
        AuditQuery auditQuery;
        auditQuery = auditReader.createQuery()
                .forRevisionsOfEntity(LandPlot.class, false,true);

        return auditQuery.getResultList();
    }

    @Override
    public void delete(Long id) {
        landPlotRepository.deleteById(id);
        landPlotRepository.flush();
    }

    @Override
    public void delete(LandPlot landPlot) {
        landPlotRepository.delete(landPlot);
        landPlotRepository.flush();
    }
}
