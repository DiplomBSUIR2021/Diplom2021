package com.diploma.demo.core.landplot.service.impl;

import com.diploma.demo.core.landplot.LandPlot;
import com.diploma.demo.core.landplot.LandPlotHistory;
import com.diploma.demo.core.landplot.repository.LandPlotHistoryRepository;
import com.diploma.demo.core.landplot.service.LandPlotHistoryService;
import com.diploma.demo.core.revinfo.RevisionEntity;
import com.diploma.demo.view.utils.TimeUtils;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LandPlotHistoryServiceImpl implements LandPlotHistoryService  {

    @Autowired
    LandPlotHistoryRepository landPlotHistoryRepository;

    @Autowired
    AuditReader auditReader;

    private LandPlotHistory getLandPlotHistoryObjectFromRevision(Object revisionObject) {
        // scratch realization
        // don't get landPlotHistory from repository
        // in this realization repository useless

        // LandPlot(id=1, address=Address(region=null, city=safsaf2, street=null ...
        //DefaultRevisionEntity(id = 2, revisionDate = 24.05.2021 17:04:05)
        //MOD  / DEL / ADD
        Object[] obj = (Object[]) revisionObject;

        LandPlot landPlot = (LandPlot) obj[0];
        RevisionEntity entity = (RevisionEntity) obj[1];

        return new LandPlotHistory(landPlot, entity, obj[2].toString());
    }

    public List<LandPlotHistory> getLandPlotHistory() {
        return getLandPlotHistory(null);
    }

    @Override
    public List<LandPlotHistory> getLandPlotHistory(Long id) {
        return getLandPlotHistory(id, null,null);
    }

    public List<LandPlotHistory> getLandPlotHistory(Long id, LocalDate startDate, LocalDate endDate) {
        AuditQuery auditQuery;
        auditQuery = auditReader.createQuery()
                .forRevisionsOfEntity(LandPlot.class, false,true);

        if (id != null) {
            System.out.println("Проверяем id");
            auditQuery.add(AuditEntity.id().eq(id));
        }

        if (startDate != null) {
            auditQuery.add(AuditEntity.revisionProperty("timestamp").gt(TimeUtils.localeDateToTimeStamp(startDate)));
        }

        if (endDate != null) {
            auditQuery.add(AuditEntity.revisionProperty("timestamp").lt(TimeUtils.localeDateToTimeStamp(endDate)));
        }

        List resultList = auditQuery.getResultList();
        List<LandPlotHistory> result = new ArrayList<>();

        for (Object o : resultList) {
            result.add(getLandPlotHistoryObjectFromRevision(o));
        }
        return result;
    }

    public LandPlotHistory getLandPlotRevision(int rev) {
        AuditQuery auditQuery;
        auditQuery = auditReader.createQuery()
                .forRevisionsOfEntity(LandPlot.class, false,true);

        auditQuery.add(AuditEntity.revisionNumber().eq(rev));

        Object searchResult = auditQuery.getSingleResult();
        return getLandPlotHistoryObjectFromRevision(searchResult);
    }

    @Override
    public void update(LandPlotHistory landPlotHistory) {
        // rewrite?
        landPlotHistory.setRevtype((short) 1);
        landPlotHistoryRepository.saveAndFlush(landPlotHistory);
    }

    @Override
    public List<LandPlotHistory> getAll() {
        TEST();
        return getLandPlotHistory();
    }

    public void TEST() {
        System.out.println("TEST START");
        List <LandPlotHistory> test = landPlotHistoryRepository.findAll();
        for (int i = 0; i < test.size(); i += 1) {
            System.out.println(test.get(i));
        }
        System.out.println("TEST END");
    }

    @Override
    public List getEntityHistory(Long id, LocalDate startDate, LocalDate endDate) {
        return getLandPlotHistory(id, startDate, endDate);
    }

    @Override
    public List getFullHistory(LocalDate startDate, LocalDate endDate) {
        return getLandPlotHistory(null, startDate, endDate);
    }

    @Override
    public Optional<LandPlotHistory> findById(Integer id) {
        return landPlotHistoryRepository.findById(id);
    }
}