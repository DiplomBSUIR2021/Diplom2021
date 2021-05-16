package com.diploma.demo.core.act.repository;

import com.diploma.demo.core.act.Act;
import javafx.collections.ObservableList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface ActRepository extends JpaRepository<Act, Long> {
    @Query("FROM Act")
    ArrayList<Act> findAll1();
}
