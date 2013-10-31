package com.springapp.mvc.service;

import com.springapp.mvc.entity.Comment;
import com.springapp.mvc.entity.Enterprise;
import com.springapp.mvc.service.IEntityService;

import java.util.List;

public interface IEnterpriseService extends IEntityService<Enterprise> {

    public List<Enterprise> getAllBy(int categoryId, int townId, boolean orderByDiscount, boolean orderByRatio, int firstIndex, int maxNumberOfItemsOnPage);

    public List<Enterprise> getAllByCategory(int categoryId, int cityId);

    public List<Enterprise> getScopeByCategory(int firstIndex, int numberOfItems, int categoryId);

    public int countEnterpriseByCategory(int categoryId);

    public int numberOfPagesForPagination(int maxNumberOfItemsOnPage, int categoryId);

    public void addComment(Comment comment);

    public List<Comment> getComments(int firmId);

    public Comment getEmptyComment(Integer userId, Integer enterpriseId);

    public void updateComment(Comment comment);

    public Comment getComment(Integer id);

    public List<Integer> getAllEnterprisesInCategories();

    Integer countEnterprises(Integer id, int townId);
}
