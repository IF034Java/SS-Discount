package com.springapp.mvc.repository.implementation;

import com.springapp.mvc.entity.CommentRatio;
import com.springapp.mvc.repository.implementation.EntityRepository;
import com.springapp.mvc.repository.ICommentRatioRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRatioRepository extends EntityRepository<CommentRatio> implements ICommentRatioRepository {

    protected CommentRatioRepository() {
        super(CommentRatio.class);
    }
}
