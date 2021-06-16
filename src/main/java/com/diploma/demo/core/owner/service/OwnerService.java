package com.diploma.demo.core.owner.service;

import com.diploma.demo.core.MyCrudService;
import com.diploma.demo.core.owner.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerService extends MyCrudService<Owner> {
    List<Owner> getAll();
    Owner add(Owner owner);
    Optional<Owner> findById(Long id);
    @Override
    void update(Owner owner);
    void delete(Long id);
    void delete(Owner owner);
}
