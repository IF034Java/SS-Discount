package com.springapp.mvc.service.implementation;// Created with IntelliJ IDEA by Yaroslav Kovbas (Xardas)

import com.springapp.mvc.entity.City;
import com.springapp.mvc.entity.Comment;
import com.springapp.mvc.repository.ICityRepository;
import com.springapp.mvc.repository.ICommentRepository;
import com.springapp.mvc.repository.IEntityRepository;
import com.springapp.mvc.service.ICommentService;
import com.springapp.mvc.service.IEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentService extends EntityService<Comment> implements ICommentService {

    @Autowired
    private ICommentRepository commentRepository;


    @Override
    protected IEntityRepository<Comment> getRepository() {
        return commentRepository;
    }
}
