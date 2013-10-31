package com.springapp.mvc.repository.implementation;

import com.springapp.mvc.entity.Role;
import com.springapp.mvc.repository.implementation.EntityRepository;
import com.springapp.mvc.repository.IRoleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepository extends EntityRepository<Role> implements IRoleRepository {

    protected RoleRepository() {
        super(Role.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Role getRole(String roleName) {
        List<Role> userList = getCurrentSession().
                createQuery(
                        "select role from Role role where role.name = '" + roleName + "'"
                ).list();
        if(!userList.isEmpty())
        {
            return (Role) userList.get(0);
        }
        else
        {
            return null;
        }
    }
}
