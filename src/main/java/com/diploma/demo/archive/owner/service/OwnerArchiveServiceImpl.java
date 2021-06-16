package com.diploma.demo.archive.owner.service;

import com.diploma.demo.archive.abstraction.service.AbstractService;
import com.diploma.demo.archive.owner.OwnerArchive;
import com.diploma.demo.archive.owner.repository.OwnerArchiveRepository;
import org.springframework.stereotype.Service;

@Service
public class OwnerArchiveServiceImpl extends AbstractService<OwnerArchive, OwnerArchiveRepository> {
    public OwnerArchiveServiceImpl(OwnerArchiveRepository repository) {
        super(repository);
    }
}