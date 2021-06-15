package com.diploma.demo.archive.owner.repository;

import com.diploma.demo.archive.abstraction.CommonRepository;
import com.diploma.demo.archive.owner.OwnerArchive;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Repository;

@Repository
@Table("owner_aud")
public interface OwnerArchiveRepository extends CommonRepository<OwnerArchive> {}
