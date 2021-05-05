package com.diploma.demo.core.landplot.service.impl;

import com.diploma.demo.core.landplot.LandPlot;
import com.diploma.demo.core.landplot.repository.LandPlotRepository;
import com.diploma.demo.core.landplot.service.LandPlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LandPlotServiceImpl implements LandPlotService {
    @Autowired
    LandPlotRepository landPlotRepository;

    @Override
    public LandPlot addLandPlot(LandPlot landPlot) {
        LandPlot savedLandPlot = landPlotRepository.saveAndFlush(landPlot);
        return savedLandPlot;
    }

    public List<LandPlot> getAll() {
        return landPlotRepository.findAll();
    }
}
