package org.cool.zoo.service;

import org.cool.zoo.entities.core.Category;
import org.cool.zoo.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by Dang Dim
 * Date     : 25-Jan-18, 5:15 PM
 * Email    : d.dim@gl-f.com
 */

@Service
public class CategoryService implements BaseServiceUtil<Category> {

    @Autowired
    private CategoryRepository category;

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return category.findAll(pageable);
    }

    @Override
    public Category  findById(Long id) {
        return category.findOne(id);
    }

    @Override
    public Category saveOrUpdate(Category entity) {
        return category.save(entity);
    }

    @Override
    public void delete(Long id) {
        category.delete(id);
    }
}
