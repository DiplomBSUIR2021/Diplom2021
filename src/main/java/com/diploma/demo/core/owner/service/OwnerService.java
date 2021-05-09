package com.diploma.demo.core.owner.service;

import com.diploma.demo.core.owner.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerService {
    List<Owner> getAll();
    Owner addOwner(Owner owner);
    Optional<Owner> findById(Long id);
    void updateOwner(Owner owner);
    void delete(Long id);
    void delete(Owner owner);
}
