package com.diploma.demo.core.landplot.service.impl;

import com.diploma.demo.core.landplot.LandPlotHistory;
import com.diploma.demo.core.landplot.repository.LandPlotHistoryRepository;
import com.diploma.demo.core.landplot.service.LandPlotHistoryService;
import org.hibernate.envers.AuditReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LandPlotHistoryServiceImpl implements LandPlotHistoryService  {

    @Autowired
    LandPlotHistoryRepository landPlotHistoryRepository;

    @Autowired
    AuditReader auditReader;

    public List<LandPlotHistory> getLandPlotHistory() {
        return landPlotHistoryRepository.findAll();
    }

    @Override
    public List<LandPlotHistory> getLandPlotHistory(@NotNull Long id) {
        return landPlotHistoryRepository.findAllById((long) id);
    }

    public java.sql.Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    public List<LandPlotHistory> getLandPlotHistory(long id, LocalDate startDate, LocalDate endDate) {
        if (startDate != null) {
            long timeStart = convertToDateViaSqlDate(startDate).getTime();
            if (endDate != null) {
                long timeEnd = convertToDateViaSqlDate(endDate).getTime();
                return landPlotHistoryRepository.findAllBetweenById(id, timeStart, timeEnd);
            }
            return landPlotHistoryRepository.findAllFromById(id, timeStart);
        }
        if (endDate != null) {
            long timeEnd = convertToDateViaSqlDate(endDate).getTime();
            return landPlotHistoryRepository.findAllToById(id, timeEnd);
        }
        return getLandPlotHistory(new Long(id));
    }

    public List<LandPlotHistory> getLandPlotHistory(LocalDate startDate, LocalDate endDate) {
        if (startDate != null) {
            long timeStart = convertToDateViaSqlDate(startDate).getTime();
            if (endDate != null) {
                long timeEnd = convertToDateViaSqlDate(endDate).getTime();
                return landPlotHistoryRepository.findAllBetween(timeStart, timeEnd);
            }
            return landPlotHistoryRepository.findAllFrom(timeStart);
        }
        if (endDate != null) {
            long timeEnd = convertToDateViaSqlDate(endDate).getTime();
            return landPlotHistoryRepository.findAllTo(timeEnd);
        }
        return landPlotHistoryRepository.findAll();
    }

    @Override
    public void update(LandPlotHistory landPlotHistory) {
        // rewrite?
        landPlotHistory.setRevtype((short) 1);
        landPlotHistoryRepository.saveAndFlush(landPlotHistory);
    }

    @Override
    public List<LandPlotHistory> getAll() {
        return landPlotHistoryRepository.findAll();
    }

    @Override
    public List getEntityHistory(Long id, LocalDate startDate, LocalDate endDate) {
        return getLandPlotHistory(id, startDate, endDate);
    }

    @Override
    public List getFullHistory(LocalDate startDate, LocalDate endDate) {
        return getLandPlotHistory( startDate, endDate);
    }

    @Override
    public Optional<LandPlotHistory> findById(Integer id) {
        return landPlotHistoryRepository.findById(id);
    }
}