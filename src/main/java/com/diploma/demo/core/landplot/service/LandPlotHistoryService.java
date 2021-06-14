package com.diploma.demo.core.landplot.service;


import com.diploma.demo.core.ArchiveService;
import com.diploma.demo.core.landplot.LandPlotHistory;

import java.time.LocalDate;
import java.util.List;

public interface LandPlotHistoryService extends ArchiveService<LandPlotHistory> {
    List<LandPlotHistory> getEntityHistory(Long id, LocalDate startDate, LocalDate endDate);
    // List<LandPlotHistory> getFullHistory(LocalDate startDate,LocalDate endDate);
}
