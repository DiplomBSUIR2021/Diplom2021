package com.diploma.demo.archive.abstraction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface CommonRepository<E extends AbstractRevEntity> extends JpaRepository<E, Integer> {
    @Query("Select a from  #{#entityName} a left join RevisionEntity b on a.rev=b.id WHERE a.id = ?1")
    public List<E> findAllById(long id);


    @Query("Select a from  #{#entityName} a left join RevisionEntity b on a.rev=b.id WHERE b.timestamp between ?1 and ?2")
    public List<E> findAllBetween(long startDate, long endDate);

    @Query("Select a from #{#entityName} a left join RevisionEntity b on a.rev=b.id" +
            " WHERE a.id = ?1 AND b.timestamp between ?2 AND ?3")
    public List<E> findAllBetweenById(long id, long startDate, long endDate);

    @Query("Select a from #{#entityName} a left join RevisionEntity b on a.rev=b.id WHERE b.timestamp > ?1")
    public List<E> findAllFrom(Long startDate);

    @Query("Select a from #{#entityName} a left join RevisionEntity b on a.rev=b.id " +
            "WHERE a.id = ?1 AND b.timestamp > ?2")
    public List<E> findAllFromById(long id, long startDate);

    @Query("Select a from #{#entityName} a  left join RevisionEntity b on a.rev=b.id WHERE b.timestamp < ?1")
    public List<E> findAllTo(Long endDate);

    @Query("Select a from #{#entityName} a  left join RevisionEntity b on a.rev=b.id " +
            "WHERE a.id = ?1 AND b.timestamp < ?2")
    public List<E> findAllToById(long id, long endDate);
}