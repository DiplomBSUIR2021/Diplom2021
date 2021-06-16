package com.diploma.demo.archive.landplot.service;

import com.diploma.demo.archive.abstraction.service.AbstractService;
import com.diploma.demo.archive.landplot.LandPlotHistory;
import com.diploma.demo.archive.landplot.repository.LandPlotHistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class LandPlotHistoryServiceImpl extends AbstractService<LandPlotHistory, LandPlotHistoryRepository> {
    public LandPlotHistoryServiceImpl(LandPlotHistoryRepository repository) {
        super(repository);
    }
}