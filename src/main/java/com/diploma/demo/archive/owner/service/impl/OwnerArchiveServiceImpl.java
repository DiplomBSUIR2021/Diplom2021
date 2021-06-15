package com.diploma.demo.archive.owner.service.impl;

import com.diploma.demo.archive.owner.OwnerArchive;
import com.diploma.demo.archive.owner.repository.OwnerArchiveRepository;
import com.diploma.demo.archive.owner.service.OwnerArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OwnerArchiveServiceImpl implements OwnerArchiveService {

    @Autowired
    OwnerArchiveRepository ownerArchiveRepository;

    public java.sql.Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    @Override
    public List<OwnerArchive> getAll() {
        return ownerArchiveRepository.findAll();
    }

    @Override
    public List<OwnerArchive> getEntityHistory(Long id, LocalDate startDate, LocalDate endDate) {
        if (id == null) {
            return getFullHistory(startDate, endDate);
        }
        if (startDate != null) {
            long timeStart = convertToDateViaSqlDate(startDate).getTime();
            if (endDate != null) {
                long timeEnd = convertToDateViaSqlDate(endDate).getTime();
                return ownerArchiveRepository.findAllBetweenById(id, timeStart, timeEnd);
            }
            return ownerArchiveRepository.findAllFromById(id, timeStart);
        }
        if (endDate != null) {
            long timeEnd = convertToDateViaSqlDate(endDate).getTime();
            return ownerArchiveRepository.findAllToById(id, timeEnd);
        }
        return getEntityHistory(new Long(id));
    }

    public List<OwnerArchive> getEntityHistory(@NotNull Long id) {
        return ownerArchiveRepository.findAllById((long) id);
    }

    public List<OwnerArchive> getFullHistory(LocalDate startDate, LocalDate endDate) {
        if (startDate != null) {
            long timeStart = convertToDateViaSqlDate(startDate).getTime();
            if (endDate != null) {
                long timeEnd = convertToDateViaSqlDate(endDate).getTime();
                return ownerArchiveRepository.findAllBetween(timeStart, timeEnd);
            }
            return ownerArchiveRepository.findAllFrom(timeStart);
        }
        if (endDate != null) {
            long timeEnd = convertToDateViaSqlDate(endDate).getTime();
            return ownerArchiveRepository.findAllTo(timeEnd);
        }
        return ownerArchiveRepository.findAll();
    }

    public List<OwnerArchive> getFullHistory() {
        return ownerArchiveRepository.findAll();
    }


    @Override
    public Optional<OwnerArchive> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void update(OwnerArchive object) {

    }
}
