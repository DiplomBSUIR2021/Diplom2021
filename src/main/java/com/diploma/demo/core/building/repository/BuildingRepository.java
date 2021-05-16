package com.diploma.demo.core.building.repository;

import com.diploma.demo.core.building.Building;
import javafx.collections.ObservableList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BuildingRepository extends JpaRepository<Building, Long> {
    //ObservableList<Building> findAll();
    //@Query("select * from building")
    //public List<Building> findAll1();
}