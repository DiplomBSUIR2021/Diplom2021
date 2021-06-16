package com.diploma.demo.archive.abstraction.service;

import com.diploma.demo.archive.abstraction.AbstractRevEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ArchiveService<T extends AbstractRevEntity> {
    List<T> getAll();
    List<T> getEntityHistory(Long id, LocalDate startDate, LocalDate endDate);
    List<T> getFullHistory(LocalDate startDate,LocalDate endDate);
    List<T> getFullHistory();
    Optional<T> findById(Integer id);
    void update(T object);
}
