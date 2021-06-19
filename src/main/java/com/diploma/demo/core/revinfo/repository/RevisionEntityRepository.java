package com.diploma.demo.core.revinfo.repository;

import com.diploma.demo.core.revinfo.RevisionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RevisionEntityRepository extends JpaRepository<RevisionEntity, Integer> {
}
