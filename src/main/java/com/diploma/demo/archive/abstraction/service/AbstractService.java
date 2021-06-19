package com.diploma.demo.archive.abstraction.service;

import com.diploma.demo.archive.abstraction.AbstractRevEntity;
import com.diploma.demo.archive.abstraction.CommonRepository;
import com.diploma.demo.core.revinfo.RevisionEntity;
import com.diploma.demo.core.revinfo.repository.RevisionEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public abstract class AbstractService <E extends AbstractRevEntity, R extends CommonRepository<E>> implements ArchiveService<E> {
    @Autowired
    R repository;

    @Autowired
    RevisionEntityRepository revisionEntityRepository;

    public AbstractService(R repository) {
        this.repository = repository;
    }

    public java.sql.Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    @Override
    public List<E> getEntityHistory(Long id, LocalDate startDate, LocalDate endDate) {
        if (id == null) {
            return getFullHistory(startDate, endDate);
        }
        if (startDate != null) {
            long timeStart = convertToDateViaSqlDate(startDate).getTime();
            if (endDate != null) {
                long timeEnd = convertToDateViaSqlDate(endDate).getTime();
                return repository.findAllBetweenById(id, timeStart, timeEnd);
            }
            return repository.findAllFromById(id, timeStart);
        }
        if (endDate != null) {
            long timeEnd = convertToDateViaSqlDate(endDate).getTime();
            return repository.findAllToById(id, timeEnd);
        }
        return getEntityHistory(new Long(id));
    }

    public List<E> getEntityHistory(@NotNull Long id) {
        return repository.findAllById((long) id);
    }

    public List<E> getFullHistory(LocalDate startDate, LocalDate endDate) {
        if (startDate != null) {
            long timeStart = convertToDateViaSqlDate(startDate).getTime();
            if (endDate != null) {
                long timeEnd = convertToDateViaSqlDate(endDate).getTime();
                return repository.findAllBetween(timeStart, timeEnd);
            }
            return repository.findAllFrom(timeStart);
        }
        if (endDate != null) {
            long timeEnd = convertToDateViaSqlDate(endDate).getTime();
            return repository.findAllTo(timeEnd);
        }
        return repository.findAll();
    }

    public List<E> getFullHistory() {
        return repository.findAll();
    }

    public List<E> getAll() {
        return repository.findAll();
    }

    public Optional<E> findById(Integer id) {
        return repository.findById(id);
    }

    public abstract E createArchiveEntityClone(E oldArchiveEntity);

    @Override
    public void update(E archiveEntity) {
        E entity = createArchiveEntityClone(archiveEntity);
        System.out.println(entity.getClass());

        // revinfo
        RevisionEntity revisionEntity = new RevisionEntity();
        revisionEntityRepository.saveAndFlush(revisionEntity);

        entity.setRevtype((short) 3);
        entity.setRevisionEntity(revisionEntity);
        entity.setRev(revisionEntity.getId());

        System.out.println(entity);
        System.out.println("rev " + entity.getRev());
        System.out.println("revisionEntity id " + revisionEntity.getId());

        repository.saveAndFlush(entity);
    }
}