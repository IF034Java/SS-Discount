package com.springapp.mvc.repository.implementation;

import com.springapp.mvc.entity.Comment;
import com.springapp.mvc.repository.implementation.EntityRepository;
import com.springapp.mvc.repository.ICommentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepository extends EntityRepository<Comment> implements ICommentRepository {

    protected CommentRepository() {
        super(Comment.class);
    }

    @Override
    public List<Comment> getAllForEnterprise(int enterpriseId) {
        return getCurrentSession().createQuery(String.format("from %s where ENTERPRISE_ID=%d",
                getClazz().getCanonicalName(),
                enterpriseId)).list();
    }
}
