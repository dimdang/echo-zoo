package org.cool.zoo.controller;

import org.cool.zoo.entities.core.Product;
import org.cool.zoo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dang Dim
 * Date     : 18-Jan-18, 1:01 PM
 * Email    : d.dim@gl-f.com
 */
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value ="/products")
    public ResponseEntity<Page<Product>> findAll() {
        Page<Product> page  = productService.findAll(new PageRequest(0,10));
        return new ResponseEntity<Page<Product>>(page, HttpStatus.OK);
    }



}
