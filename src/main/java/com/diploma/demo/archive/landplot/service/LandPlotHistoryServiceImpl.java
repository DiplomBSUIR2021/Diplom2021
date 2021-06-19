package com.diploma.demo.archive.landplot.service;

import com.diploma.demo.archive.abstraction.service.AbstractService;
import com.diploma.demo.archive.landplot.LandPlotHistory;
import com.diploma.demo.archive.landplot.repository.LandPlotHistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class LandPlotHistoryServiceImpl extends AbstractService<LandPlotHistory, LandPlotHistoryRepository> {
    public LandPlotHistoryServiceImpl(LandPlotHistoryRepository repository) {
        super(repository);
    }

    @Override
    public LandPlotHistory createArchiveEntityClone(LandPlotHistory oldArchiveEntity) {
        /*LandPlotHistory landPlotHistory = new LandPlotHistory();

        landPlotHistory.setId(oldArchiveEntity.getId());
        landPlotHistory.setAddress(oldArchiveEntity.getAddress());
        landPlotHistory.setCadastralNumber(oldArchiveEntity.getCadastralNumber());
        landPlotHistory.setCategory(oldArchiveEntity.getCategory());
        landPlotHistory.setCurrentMarks(oldArchiveEntity.getCurrentMarks());
        landPlotHistory.setIntendedUse(oldArchiveEntity.getIntendedUse());
        landPlotHistory.setLandPlotPurpose(oldArchiveEntity.getLandPlotPurpose());
        landPlotHistory.setNotes(oldArchiveEntity.getNotes());
        landPlotHistory.setSurface(oldArchiveEntity.getSurface());*/
        LandPlotHistory cloneArchiveEntity= null;
        try {
            cloneArchiveEntity = oldArchiveEntity.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return cloneArchiveEntity;
    }
}