package com.diploma.demo.core;


import java.util.List;
import java.util.Optional;

public interface MyCrudService<T> {
    void delete(Long id);
    List<T> getAll();
    List getRevisions(Long id);
}
