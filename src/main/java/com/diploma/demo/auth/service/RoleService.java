package com.diploma.demo.auth.service;

import com.diploma.demo.auth.Role;
import com.diploma.demo.auth.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
