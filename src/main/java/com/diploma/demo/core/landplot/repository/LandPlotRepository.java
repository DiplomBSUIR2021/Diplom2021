package com.diploma.demo.core.landplot.repository;

import com.diploma.demo.core.landplot.LandPlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Repository;

@Repository
@Table("land_plot")
public interface LandPlotRepository extends JpaRepository<LandPlot, Long>, LandPlotRepositoryCustom {

}