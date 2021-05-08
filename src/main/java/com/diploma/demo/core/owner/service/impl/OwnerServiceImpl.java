package com.diploma.demo.core.owner.service.impl;

import com.diploma.demo.core.owner.Owner;
import com.diploma.demo.core.owner.repository.OwnerRepository;
import com.diploma.demo.core.owner.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    OwnerRepository ownerRepository;

    @Override
    public List<Owner> getAll() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner addOwner(Owner owner) {
        return ownerRepository.saveAndFlush(owner);
    }
}
