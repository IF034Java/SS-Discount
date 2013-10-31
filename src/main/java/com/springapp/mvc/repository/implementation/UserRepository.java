package com.springapp.mvc.repository.implementation;

import com.springapp.mvc.entity.User;
import com.springapp.mvc.repository.implementation.EntityRepository;
import com.springapp.mvc.repository.IUserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository extends EntityRepository<User> implements IUserRepository {

    protected UserRepository() {
        super(User.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUser(String nickname) {
        List<User> userList = getCurrentSession().
                createQuery(
                        "select user from User user where user.nickname = '" + nickname + "'"
                ).list();
        if(!userList.isEmpty())
        {
            return (User) userList.get(0);        
        }
        else
        {
            return null;
        }
    }

    @Override
    public User getUserByOpenIdIdentity(String identity) {
        List<User> userList = getCurrentSession().
                createQuery(
                        "select user from User user where user.openIdIdentity = '" + identity + "'"
                ).list();
        if(!userList.isEmpty())
        {
            return (User) userList.get(0);
        }
        else
        {
            return null;
        }
    }
}
