package com.diploma.demo.core.landplot.repository;

import com.diploma.demo.core.landplot.LandPlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityResult;
import javax.persistence.NamedNativeQueries;
import javax.persistence.SqlResultSetMapping;
import java.util.List;

@Repository
@Table("land_plot")
public interface LandPlotRepository extends JpaRepository<LandPlot, Long> {

    // @Query("Delete from LandPlot a where a.year < '2022-01-01'")
    // @NamedNativeQueries()

    /*ALTER TABLE land_plot DETACH PARTITION land_plot_y2022;*/
    /*@Query(value  = "",
            nativeQuery = true)
    public void detach();*/

    @Query(value  = "ALTER TABLE land_plot DETACH PARTITION land_plot_y2021; DROP table land_plot_y2021",
            nativeQuery = true)
    public void customDelete(long id);

}