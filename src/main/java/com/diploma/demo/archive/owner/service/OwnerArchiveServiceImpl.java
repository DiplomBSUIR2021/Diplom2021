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

    @Override
    public OwnerArchive createArchiveEntityClone(OwnerArchive oldArchiveEntity) {
        OwnerArchive cloneArchiveEntity= null;
        try {
            cloneArchiveEntity = oldArchiveEntity.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return cloneArchiveEntity;
    }
}