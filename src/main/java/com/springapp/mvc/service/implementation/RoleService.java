package com.springapp.mvc.service.implementation;// Created with IntelliJ IDEA by Yaroslav Kovbas (Xardas)

import com.springapp.mvc.entity.Role;
import com.springapp.mvc.repository.IEntityRepository;
import com.springapp.mvc.repository.IRoleRepository;
import com.springapp.mvc.repository.implementation.RoleRepository;
import com.springapp.mvc.service.implementation.EntityService;
import com.springapp.mvc.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleService extends EntityService<Role> implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    protected IEntityRepository<Role> getRepository() {
        return roleRepository;
    }

    @Override
    public Role getRole(String roleName) {
       return roleRepository.getRole(roleName); //TODO correct implementation
    }
}