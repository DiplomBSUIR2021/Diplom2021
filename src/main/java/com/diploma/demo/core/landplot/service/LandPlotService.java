package com.diploma.demo.core.landplot.service;

import com.diploma.demo.core.landplot.LandPlot;

import java.util.List;
import java.util.Optional;

public interface LandPlotService {
    LandPlot addLandPlot(LandPlot landPlot);
    void updateLandPlot(LandPlot landPlot);
    Optional<LandPlot> getById(Long id);
    List<LandPlot> getAll();
}
