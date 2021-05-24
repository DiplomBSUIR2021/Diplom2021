package com.diploma.demo.core.landplot.repository.impl;

import com.diploma.demo.core.ServiceController;
import com.diploma.demo.core.landplot.LandPlot;
import com.diploma.demo.core.landplot.LandPlotHistory;
import com.diploma.demo.core.landplot.repository.LandPlotHistoryService;
import com.diploma.demo.view.utils.CrudUtils;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;

import java.util.ArrayList;
import java.util.List;

public class LandPlotHistoryServiceImpl extends CrudUtils implements LandPlotHistoryService  {

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
}
