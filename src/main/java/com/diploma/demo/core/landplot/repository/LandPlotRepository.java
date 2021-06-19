package com.diploma.demo.core.landplot.repository;

import com.diploma.demo.core.landplot.LandPlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    //  Select * from land_plot WHERE the_year < extract(year from current_date);
    @Query(value  = "Select * from land_plot WHERE the_year < extract(year from current_date) + 2;",
            nativeQuery = true)
    // @Query("Select a from  #{#entityName} a WHERE a.year < ?1")
    List<LandPlot> getOldPlots(int year);


    @Modifying
    @Transactional
    @Query(value  = "ALTER TABLE IF EXISTS land_plot DETACH PARTITION land_plot_y2022;",
            nativeQuery = true)
     void detacPartion();

    @Modifying
    @Transactional
    @Query(value  = " DROP table land_plot_y2022",
            nativeQuery = true)
    void customDelete();

}