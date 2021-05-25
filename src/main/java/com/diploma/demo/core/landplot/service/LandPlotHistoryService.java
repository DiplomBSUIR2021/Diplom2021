package com.diploma.demo.core.landplot.service;

import com.diploma.demo.core.landplot.LandPlotHistory;

import java.util.List;

public interface LandPlotHistoryService {
    List<LandPlotHistory> getLandPlotHistory(Long id);
    List<LandPlotHistory> getLandPlotHistory();
}
