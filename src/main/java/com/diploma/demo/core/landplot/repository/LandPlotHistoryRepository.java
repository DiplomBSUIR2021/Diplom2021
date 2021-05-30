package com.diploma.demo.core.landplot.repository;

import com.diploma.demo.core.landplot.LandPlotHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Table("land_plot_aud")
public interface LandPlotHistoryRepository  extends JpaRepository<LandPlotHistory, Integer>  {

    @Query("Select a from LandPlotHistory a  left join RevisionEntity b on a.rev=b.id WHERE a.id = ?1")
    public List<LandPlotHistory> findAllById(long id);


    @Query("Select a from LandPlotHistory a  left join RevisionEntity b on a.rev=b.id WHERE b.timestamp between ?1 and ?2")
    public List<LandPlotHistory> findAllBetween(long startDate, long endDate);

    @Query("Select a from LandPlotHistory a  left join RevisionEntity b on a.rev=b.id" +
            " WHERE a.id = ?1 AND b.timestamp between ?2 AND ?3")
    public List<LandPlotHistory> findAllBetweenById(long id, long startDate, long endDate);

    @Query("Select a from LandPlotHistory a  left join RevisionEntity b on a.rev=b.id WHERE b.timestamp > ?1")
    public List<LandPlotHistory> findAllFrom(Long startDate);

    @Query("Select a from LandPlotHistory a  left join RevisionEntity b on a.rev=b.id " +
            "WHERE a.id = ?1 AND b.timestamp > ?2")
    public List<LandPlotHistory> findAllFromById(long id, long startDate);

    @Query("Select a from LandPlotHistory a  left join RevisionEntity b on a.rev=b.id WHERE b.timestamp < ?1")
    public List<LandPlotHistory> findAllTo(Long endDate);

    @Query("Select a from LandPlotHistory a  left join RevisionEntity b on a.rev=b.id " +
            "WHERE a.id = ?1 AND b.timestamp < ?2")
    public List<LandPlotHistory> findAllToById(long id, long endDate);

}
