package com.diploma.demo.core.landplot.repository;

import com.diploma.demo.core.landplot.LandPlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Table("land_plot")
public interface LandPlotRepository extends JpaRepository<LandPlot, Long> {
    @Query(value = "select distinct on ( EXTRACT(YEAR from the_year)) * from land_plot", nativeQuery = true)
    public List<LandPlot> findDis();

}