package com.diploma.demo.core.landplot.service;

import com.diploma.demo.core.landplot.LandPlot;

import java.util.List;

public interface LandPlotService {
    LandPlot addLandPlot(LandPlot landPlot);
    List<LandPlot> getAll();
}
