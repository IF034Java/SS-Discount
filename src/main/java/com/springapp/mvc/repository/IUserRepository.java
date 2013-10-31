package com.springapp.mvc.repository;

import com.springapp.mvc.entity.User;
import com.springapp.mvc.repository.IEntityRepository;

public interface IUserRepository extends IEntityRepository<User> {

    public User getUser(String nickname);

    User getUserByOpenIdIdentity(String identity);
}
