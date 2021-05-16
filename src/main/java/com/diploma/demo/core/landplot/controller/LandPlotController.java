package com.diploma.demo.core.landplot.controller;


import com.diploma.demo.core.landplot.repository.LandPlotRepository;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("land-plot.fxml")
public class LandPlotController {

    @Autowired
    LandPlotRepository landPlotRepository;
}
