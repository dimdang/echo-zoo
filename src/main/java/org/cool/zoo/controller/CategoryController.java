package org.cool.zoo.controller;

import org.cool.zoo.configure.Routes;
import org.cool.zoo.entities.core.Category;
import org.cool.zoo.entities.response.JResponseEntity;
import org.cool.zoo.service.CategoryService;
import org.cool.zoo.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dang Dim
 * Date     : 25-Jan-18, 4:07 PM
 * Email    : d.dim@gl-f.com
 */

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = Routes.PRODUCT, method = RequestMethod.POST)
    public JResponseEntity<Object> saveProducts(Category category) {
        if (category != null) {
            categoryService.saveOrUpdate(category);
            return ResponseFactory.build("CATEGORY SAVED", HttpStatus.OK, category);
        } else {
            return ResponseFactory.build("CATEGORY SAVED FAIL", HttpStatus.NOT_FOUND);
        }
    }
}
