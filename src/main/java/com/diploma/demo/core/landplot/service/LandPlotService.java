package com.diploma.demo.core.landplot.service;

import com.diploma.demo.core.MyCrudService;
import com.diploma.demo.core.landplot.LandPlot;

import java.util.List;
import java.util.Optional;

public interface LandPlotService extends MyCrudService<LandPlot> {
    LandPlot add(LandPlot landPlot);
    void update(LandPlot landPlot);
    Optional<LandPlot> findById(Long id);
    List<LandPlot> getAll();
    void delete(Long id);
    void delete(LandPlot landPlot);
    // LandPlot add (LandPlot landPlot);

}
