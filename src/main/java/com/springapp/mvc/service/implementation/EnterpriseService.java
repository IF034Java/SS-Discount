package com.springapp.mvc.service.implementation;

import com.springapp.mvc.entity.Category;
import com.springapp.mvc.entity.Comment;
import com.springapp.mvc.entity.Enterprise;
import com.springapp.mvc.entity.User;
import com.springapp.mvc.repository.ICategoryRepository;
import com.springapp.mvc.repository.IEntityRepository;
import com.springapp.mvc.repository.ICommentRepository;
import com.springapp.mvc.repository.IEnterpriseRepository;
import com.springapp.mvc.service.implementation.EntityService;
import com.springapp.mvc.service.IEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EnterpriseService extends EntityService<Enterprise> implements IEnterpriseService {

    @Autowired
    private IEnterpriseRepository enterpriseRepository;

    @Autowired
    private ICommentRepository commentRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    protected IEntityRepository<Enterprise> getRepository() {
        return enterpriseRepository;
    }

    @Override
    public List<Enterprise> getAllBy(int categoryId, int townId, boolean orderByDiscount, boolean orderByRatio, int firstIndex, int maxNumberOfItemsOnPage) {
        return enterpriseRepository.getAllBy(categoryId, townId, orderByDiscount, orderByRatio, firstIndex, maxNumberOfItemsOnPage);
    }

    @Override
    public List<Enterprise> getAllByCategory(int categoryId, int cityId){
        return enterpriseRepository.getAllByCategory(categoryId, cityId);
    }

    @Override
    public List<Enterprise> getScopeByCategory(int firstIndex, int numberOfItems, int categoryId) {
        return enterpriseRepository.getScopeByCategory(firstIndex, numberOfItems, categoryId);
    }

    @Override
    public int countEnterpriseByCategory(int categoryId) {
        return enterpriseRepository.countEnterpriseByCategory(categoryId);
    }

    @Override
    public int numberOfPagesForPagination(int maxNumberOfItemsOnPage, int categoryId){
        if (countEnterpriseByCategory(categoryId)%maxNumberOfItemsOnPage!=0){
            return countEnterpriseByCategory(categoryId)/maxNumberOfItemsOnPage+1;
        } else{
            return countEnterpriseByCategory(categoryId)/maxNumberOfItemsOnPage;
        }
    }

    @Override
    public void addComment(Comment comment) {
        commentRepository.add(comment);
    }

    @Override
    public List<Comment> getComments(int enterpriseId) {
       return commentRepository.getAllForEnterprise(enterpriseId);
    }

    @Override
    public Comment getEmptyComment(Integer userId, Integer enterpriseId) {
        Enterprise enterprise = new Enterprise();
        enterprise.setId(enterpriseId);
        User user = new User();
        user.setId(userId);
        Comment comment = new Comment();
        comment.setEnterprise(enterprise);
        comment.setUser(user);
        return comment;
    }

    @Override
    public void updateComment(Comment comment) {
        commentRepository.update(comment);
    }

    @Override
    public Comment getComment(Integer id) {
       return commentRepository.get(id);
    }

    @Override
    public List<Integer> getAllEnterprisesInCategories()
    {
        List<Integer> result = new ArrayList<Integer>();
        for (Category category : categoryRepository.getAll())
        {
              result.add(countEnterpriseByCategory(category.getId()));
        }
        return result;
    }

    @Override
    public Integer countEnterprises(Integer id, int townId) {
        return enterpriseRepository.countEnterprises(id, townId);
    }
}
