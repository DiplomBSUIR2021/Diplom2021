package com.diploma.demo.core.owner.service.impl;

import com.diploma.demo.core.owner.Owner;
import com.diploma.demo.core.owner.repository.OwnerRepository;
import com.diploma.demo.core.owner.service.OwnerService;
import com.diploma.demo.view.utils.TimeUtils;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    AuditReader auditReader;

    @Override
    public List<Owner> getAll() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner addOwner(Owner owner) {
        return ownerRepository.saveAndFlush(owner);
    }

    @Override
    public Optional<Owner> findById(Long id) {
        return ownerRepository.findById(id);
    }

    @Override
    public void update(Owner owner) {
        ownerRepository.saveAndFlush(owner);
    }

    @Override
    public void delete(Long id) {
        ownerRepository.deleteById(id);
    }

    @Override
    public void delete(Owner owner) {
        ownerRepository.delete(owner);
    }

    @Override
    public List getRevisions(Long id, LocalDate startDate, LocalDate endDate) {
        AuditQuery auditQuery;
        auditQuery = auditReader.createQuery()
                .forRevisionsOfEntity(Owner.class, false,true);

        auditQuery.add(AuditEntity.id().eq(id));

        if (startDate != null) {
            auditQuery.add(AuditEntity.revisionProperty("timestamp").gt(TimeUtils.localeDateToTimeStamp(startDate)));
        }

        if (endDate != null) {
            auditQuery.add(AuditEntity.revisionProperty("timestamp").lt(TimeUtils.localeDateToTimeStamp(endDate)));
        }

        return auditQuery.getResultList();
    }

    @Override
    public List getAllRevisions(LocalDate startDate,LocalDate endDate) {
        AuditQuery auditQuery;
        auditQuery = auditReader.createQuery()
                .forRevisionsOfEntity(Owner.class, false,true);

        if (startDate != null) {
            auditQuery.add(AuditEntity.revisionProperty("timestamp").gt(TimeUtils.localeDateToTimeStamp(startDate)));
        }

        if (endDate != null) {
            auditQuery.add(AuditEntity.revisionProperty("timestamp").lt(TimeUtils.localeDateToTimeStamp(endDate)));
        }

        return auditQuery.getResultList();
    }
}
