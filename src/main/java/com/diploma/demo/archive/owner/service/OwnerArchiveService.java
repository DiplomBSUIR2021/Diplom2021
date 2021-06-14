package com.diploma.demo.archive.owner.service;

import com.diploma.demo.archive.owner.OwnerArchive;
import com.diploma.demo.core.ArchiveService;

import java.time.LocalDate;
import java.util.List;

public interface OwnerArchiveService extends ArchiveService<OwnerArchive> {
    List<OwnerArchive> getEntityHistory(Long id, LocalDate startDate, LocalDate endDate);
    // List<OwnerArchive> getFullHistory(LocalDate startDate,LocalDate endDate);
}
