package com.diploma.demo.core;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ArchiveService<T> {
    List<T> getAll();
    List<T> getEntityHistory(Long id, LocalDate startDate, LocalDate endDate);
    // List<T> getFullHistory(LocalDate startDate,LocalDate endDate);
    Optional<T> findById(Integer id);
    void update(T object);
}
