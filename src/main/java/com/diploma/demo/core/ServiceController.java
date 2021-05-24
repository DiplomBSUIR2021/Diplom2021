package com.diploma.demo.core;

import com.diploma.demo.core.landplot.service.impl.LandPlotServiceImpl;

public class ServiceController {
    static LandPlotServiceImpl landPlotService;

    public static LandPlotServiceImpl getLandPlotService() {
        return landPlotService;
    }

    public static void setLandPlotService(LandPlotServiceImpl landPlotServiceNew) {
        landPlotService = landPlotServiceNew;
    }

}
