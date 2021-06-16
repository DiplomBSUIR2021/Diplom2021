package com.diploma.demo.core;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MyCrudService<T> {
    void delete(Long id);
    List<T> getAll();
    List getRevisions(Long id, LocalDate startDate,LocalDate endDate);
    List getAllRevisions(LocalDate startDate,LocalDate endDate);
    Optional<T> findById(Long id);
    void update(T object);
    T add(T object);
}
