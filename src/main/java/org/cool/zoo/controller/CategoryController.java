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
import org.springframework.web.bind.annotation.*;

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
    public JResponseEntity<Category> findCategoryById(@PathVariable(value = "id") Long id) {
        Category category = categoryService.findById(id);
        if (category != null) {
            return ResponseFactory.build("SUCCESS", HttpStatus.OK, category);
        } else
            return ResponseFactory.build("FAILED", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.GET)
    public JResponseEntity<Page<Category>> getAllCategory(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
        Page<Category> categories = categoryService.findAll(new PageRequest(page, size));
        if (categories != null) {
            return ResponseFactory.build("ALL CATEGORY FOUNDED", HttpStatus.OK, categories);
        } else
            return ResponseFactory.build("NOT FOUND", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = Routes.ID, method = RequestMethod.DELETE)
    public JResponseEntity<Category> delete(@PathVariable(value = "id") Long id) {
        if (id != null) {
            categoryService.delete(id);
            return ResponseFactory.build("DELETED SUCCESSFUL", HttpStatus.OK);
        } else
            return ResponseFactory.build("DELETE FAILED", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = Routes.ID, method = RequestMethod.PUT)
    public JResponseEntity<Object> updateProduct(@RequestBody Category category, Long id) {
        Category existCategory = categoryService.findById(id);
        if (existCategory == null)
            return ResponseFactory.build("Category not found", HttpStatus.NOT_FOUND);

        categoryService.saveOrUpdate(category);
        return ResponseFactory.build("UPDATE SUCCESS", HttpStatus.OK);

    }

}
