package com.diploma.demo.archive.owner.repository;

import com.diploma.demo.archive.owner.OwnerArchive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Table("owner_aud")
public interface OwnerArchiveRepository extends JpaRepository<OwnerArchive, Integer> {
    @Query("Select a from OwnerArchive a  left join RevisionEntity b on a.rev=b.id WHERE a.id = ?1")
    public List<OwnerArchive> findAllById(long id);


    @Query("Select a from OwnerArchive a  left join RevisionEntity b on a.rev=b.id WHERE b.timestamp between ?1 and ?2")
    public List<OwnerArchive> findAllBetween(long startDate, long endDate);

    @Query("Select a from OwnerArchive a  left join RevisionEntity b on a.rev=b.id" +
            " WHERE a.id = ?1 AND b.timestamp between ?2 AND ?3")
    public List<OwnerArchive> findAllBetweenById(long id, long startDate, long endDate);

    @Query("Select a from OwnerArchive a  left join RevisionEntity b on a.rev=b.id WHERE b.timestamp > ?1")
    public List<OwnerArchive> findAllFrom(Long startDate);

    @Query("Select a from OwnerArchive a  left join RevisionEntity b on a.rev=b.id " +
            "WHERE a.id = ?1 AND b.timestamp > ?2")
    public List<OwnerArchive> findAllFromById(long id, long startDate);

    @Query("Select a from OwnerArchive a  left join RevisionEntity b on a.rev=b.id WHERE b.timestamp < ?1")
    public List<OwnerArchive> findAllTo(Long endDate);

    @Query("Select a from OwnerArchive a  left join RevisionEntity b on a.rev=b.id " +
            "WHERE a.id = ?1 AND b.timestamp < ?2")
    public List<OwnerArchive> findAllToById(long id, long endDate);

}
