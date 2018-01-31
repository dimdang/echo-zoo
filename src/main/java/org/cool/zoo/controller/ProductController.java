package org.cool.zoo.controller;

import com.sun.org.apache.regexp.internal.RE;
import org.cool.zoo.configure.Routes;
import org.cool.zoo.entities.core.Category;
import org.cool.zoo.entities.core.Product;
import org.cool.zoo.entities.response.JResponseEntity;
import org.cool.zoo.entities.users.Role;
import org.cool.zoo.repositories.CategoryRepository;
import org.cool.zoo.repositories.ProductRepository;
import org.cool.zoo.service.CategoryService;
import org.cool.zoo.service.ProductService;
import org.cool.zoo.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Dang Dim
 * Date     : 18-Jan-18, 1:01 PM
 * Email    : d.dim@gl-f.com
 */

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value = Routes.PRODUCT, method = RequestMethod.GET)
    public JResponseEntity<Page<Product>> getProducts(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size)
    {
        Page<Product> products = productService.findAll(new PageRequest(page, size));
        if (products != null) {
            return ResponseFactory.build("SUCCESS", HttpStatus.OK, products);
        } else
            return ResponseFactory.build("FAILED", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = Routes.PRODUCT_ID, method = RequestMethod.GET)
    public JResponseEntity<Product> findProductById(@PathVariable(value = "id") long id) {
        if (id > 0) {
            Product product = productService.findById(id);
            return ResponseFactory.build("SUCCESSFUL", HttpStatus.OK, product);
        } else
            return ResponseFactory.build("FAILED", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = Routes.PRODUCT, method = RequestMethod.POST)
    public JResponseEntity<Product> saveProducts(Product product) {
        if (product != null) {
            Category category = categoryService.findById(product.getCategory().getId());
            if (category != null) {
                product.setCategory(category);
                productService.saveOrUpdate(product);
                return ResponseFactory.build("PRODUCT ADDED SUCCESSFUL", HttpStatus.OK, product);
            }

        } else {
            return ResponseFactory.build("NO PRODUCT TO ADD", HttpStatus.NOT_FOUND);
        }
        return ResponseFactory.build("SOME THING WENT WRONG PLEASE CONTRACT BUCKY DEV", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = Routes.PRODUCT_ID, method = RequestMethod.DELETE)
    public JResponseEntity<Product> deleteProduct(@PathVariable(value = "id") long id) {
        if (id > 0) {
            productService.delete(id);
            return ResponseFactory.build("DELETED SUCCESS", HttpStatus.OK);
        } else
            return ResponseFactory.build("DELETED FAILED", HttpStatus.NOT_FOUND);
    }


    @RequestMapping(value = Routes.PRODUCT_CATEGORY_NAME, method = RequestMethod.GET)
    public JResponseEntity<Page<Product>> findProductsByCategory(
            @RequestParam("category") String name,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size
    ) {
        Category category = categoryRepository.findByCategoryName(name);
        Page<Product> products = null;
        if (category != null){
            products = productRepository.findAllByCategoryEquals(category, new PageRequest(page, size));
            return ResponseFactory.build("PRODUCTS FOUND", HttpStatus.OK, products);
        }else {
            return ResponseFactory.build("PRODUCT NOT AVAILABLE", HttpStatus.OK);
        }
    }

}
