package com.springapp.mvc.service;

import com.springapp.mvc.entity.Role;
import com.springapp.mvc.service.IEntityService;

public interface IRoleService extends IEntityService<Role> {
    public Role getRole(String roleName);
}

