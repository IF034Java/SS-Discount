package com.springapp.mvc.service;

import com.springapp.mvc.entity.Role;
import com.springapp.mvc.entity.User;
import com.springapp.mvc.service.IEntityService;

import java.util.List;

public interface IUserService extends IEntityService<User> {
    public List<Role> getAllRoles();
    public Role getRole(int roleId);
    public User getUser(String username);
    public User getUserByOpenIdIdentity(String identity);
}
