package com.springapp.mvc.repository;

import com.springapp.mvc.entity.Comment;
import com.springapp.mvc.repository.IEntityRepository;

import java.util.List;

public interface ICommentRepository extends IEntityRepository<Comment> {

    public List<Comment> getAllForEnterprise(int enterpriseId);
}
