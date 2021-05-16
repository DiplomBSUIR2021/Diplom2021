package com.diploma.demo.core.landplot.repository;

import com.diploma.demo.core.landplot.LandPlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LandPlotRepository extends JpaRepository<LandPlot, Long> {
}