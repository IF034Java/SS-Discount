package com.springapp.mvc.service.implementation;

import com.springapp.mvc.entity.Role;
import com.springapp.mvc.entity.User;
import com.springapp.mvc.repository.IEntityRepository;
import com.springapp.mvc.repository.IRoleRepository;
import com.springapp.mvc.repository.IUserRepository;
import com.springapp.mvc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService extends EntityService<User> implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    protected IEntityRepository<User> getRepository() {
        return userRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.getAll();
    }

    @Override
    public Role getRole(int roleId) {
        return roleRepository.get(roleId);
    }

    @Override
    public User getUser(String nickname) {
        return userRepository.getUser(nickname);
    }

    @Override
    public User getUserByOpenIdIdentity(String identity) {
        return userRepository.getUserByOpenIdIdentity(identity);
    }
}
