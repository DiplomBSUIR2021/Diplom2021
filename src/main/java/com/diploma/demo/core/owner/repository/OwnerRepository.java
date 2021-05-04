package com.diploma.demo.core.owner.repository;

import com.diploma.demo.core.owner.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    List<Owner>  findAllByName(String name);


}
