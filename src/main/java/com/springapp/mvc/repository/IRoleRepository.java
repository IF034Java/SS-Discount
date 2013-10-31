package com.springapp.mvc.repository;

import com.springapp.mvc.entity.Role;
import com.springapp.mvc.repository.IEntityRepository;

public interface IRoleRepository extends IEntityRepository<Role> {

    @SuppressWarnings("unchecked")
    Role getRole(String roleName);
}
