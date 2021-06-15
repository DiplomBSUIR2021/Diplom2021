package com.diploma.demo.archive.landplot.repository;

import com.diploma.demo.archive.abstraction.CommonRepository;
import com.diploma.demo.archive.landplot.LandPlotHistory;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Repository;

@Repository
@Table("land_plot_aud")
public interface LandPlotHistoryRepository  extends CommonRepository<LandPlotHistory> {
}
