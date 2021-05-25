package com.diploma.demo.core.landplot.service.impl;

import com.diploma.demo.core.ServiceController;
import com.diploma.demo.core.landplot.LandPlot;
import com.diploma.demo.core.landplot.LandPlotHistory;
import com.diploma.demo.core.landplot.repository.LandPlotHistoryRepository;
import com.diploma.demo.core.landplot.repository.LandPlotRepository;
import com.diploma.demo.core.landplot.service.LandPlotHistoryService;
import com.diploma.demo.view.utils.CrudUtils;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LandPlotHistoryServiceImpl extends CrudUtils implements LandPlotHistoryService  {

    @Autowired
    LandPlotHistoryRepository landPlotHistoryRepository;

    @Autowired
    AuditReader auditReader;



    public List<LandPlotHistory> getLandPlotHistory() {
        return getLandPlotHistory(null);
    }

    @Override
    public List<LandPlotHistory> getLandPlotHistory(Long id) {
        AuditReader auditReader = ServiceController.getLandPlotService().getAuditReader();

        AuditQuery auditQuery;
        auditQuery = auditReader.createQuery()
                .forRevisionsOfEntity(LandPlot.class, false,true);

        if (id != null) {
            auditQuery.add(AuditEntity.id().eq(id));
        }

        List resultList = auditQuery.getResultList();
        List<LandPlotHistory> result = new ArrayList<>();

        for (int i = 0; i < resultList.size(); i += 1) {
            Object fullObj = resultList.get(i);
            Object[] obj = (Object[]) fullObj;
            // LandPlot(id=1, address=Address(region=null, city=safsaf2, street=null ...
            //DefaultRevisionEntity(id = 2, revisionDate = 24.05.2021 17:04:05)
            //MOD  / DEL / ADD
            LandPlot landPlot = (LandPlot) obj[0];
            DefaultRevisionEntity entity = (DefaultRevisionEntity) obj[1];
            System.out.println("чекает время");
            System.out.println(entity.getRevisionDate().getClass());

            result.add(new LandPlotHistory(landPlot, entity, obj[2].toString()));
        }
        return result;
    }

    public LandPlotHistory getLandPlotRevision(int rev) {
        System.out.println("test ar");
        System.out.println(auditReader);

        System.out.println("test rep");
        System.out.println(landPlotHistoryRepository);
        AuditReader auditReader = ServiceController.getLandPlotService().getAuditReader();

        AuditQuery auditQuery;
        auditQuery = auditReader.createQuery()
                .forRevisionsOfEntity(LandPlot.class, false,true);

        auditQuery.add(AuditEntity.revisionNumber().eq(rev));

        Object searchResult = auditQuery.getSingleResult();
        Object[] result = (Object[]) searchResult;
        LandPlot landPlot = (LandPlot) result[0];
        DefaultRevisionEntity entity = (DefaultRevisionEntity) result[1];

        return new LandPlotHistory(landPlot, entity, result[2].toString());
    }

    public void updateLandPlotRevision(LandPlotHistory landPlotHistory) {

    }

}
