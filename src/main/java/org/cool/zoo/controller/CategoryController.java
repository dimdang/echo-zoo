package org.cool.zoo.controller;

import org.cool.zoo.configure.Routes;
import org.cool.zoo.entities.core.Category;
import org.cool.zoo.entities.response.JResponseEntity;
import org.cool.zoo.service.CategoryService;
import org.cool.zoo.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dang Dim
 * Date     : 25-Jan-18, 4:07 PM
 * Email    : d.dim@gl-f.com
 */

@RestController
@RequestMapping(Routes.CATEGORY)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.POST)
    public JResponseEntity<Category> saveCategory(Category category) {
        if (category != null) {
            categoryService.saveOrUpdate(category);
            return ResponseFactory.build("CATEGORY CREATED", HttpStatus.OK, category);
        } else {
            return ResponseFactory.build("MAKE SURE YOUR WANT TO CREATE", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = Routes.ID, method = RequestMethod.GET)
    public JResponseEntity<Category> findCategoryById(@PathVariable(value = "id") Long id){
        if (id != null){
            Category category = categoryService.findById(id);
            return ResponseFactory.build("SUCCESS", HttpStatus.OK, category);
        }else
            return ResponseFactory.build("FAILED", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.GET)
    public JResponseEntity<Page<Category>> getAllCategory(){
        Page<Category> categories = categoryService.findAll(new PageRequest(0, 10));
        if (categories != null){
            return ResponseFactory.build("ALL CATEGORY FOUNDED", HttpStatus.OK, categories);
        }else
            return ResponseFactory.build("NOT FOUND", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = Routes.ID, method = RequestMethod.DELETE)
    public JResponseEntity<Category> delete(@PathVariable(value = "id") Long id){
        if (id != null){
            return ResponseFactory.build("DELETED SUCCESSFUL", HttpStatus.OK);
        }else
            return ResponseFactory.build("DELETE FAILED", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public JResponseEntity<Object> updateProduct(Category category) {
        if ( category != null && category.getId() > 0) {
            categoryService.saveOrUpdate(category);
            return ResponseFactory.build("UPDATE SUCCESS", HttpStatus.OK);
        }
        return ResponseFactory.build("Please specify category you want to update..!", HttpStatus.NOT_FOUND);
    }

}
