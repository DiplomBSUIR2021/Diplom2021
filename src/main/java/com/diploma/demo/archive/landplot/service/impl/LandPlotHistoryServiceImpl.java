package com.diploma.demo.archive.landplot.service.impl;

import com.diploma.demo.archive.landplot.LandPlotHistory;
import com.diploma.demo.archive.landplot.repository.LandPlotHistoryRepository;
import com.diploma.demo.archive.landplot.service.LandPlotHistoryService;
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

    public java.sql.Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
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

    public List<LandPlotHistory> getEntityHistory(@NotNull Long id) {
        return landPlotHistoryRepository.findAllById((long) id);
    }

    @Override
    public List<LandPlotHistory> getEntityHistory(Long id, LocalDate startDate, LocalDate endDate) {
        if (id == null) {
            return getFullHistory(startDate, endDate);
        }
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
        return getEntityHistory(new Long(id));
    }

    public List<LandPlotHistory> getFullHistory() {
        return landPlotHistoryRepository.findAll();
    }

    public List<LandPlotHistory> getFullHistory(LocalDate startDate, LocalDate endDate) {
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
    public Optional<LandPlotHistory> findById(Integer id) {
        return landPlotHistoryRepository.findById(id);
    }
}