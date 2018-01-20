package org.cool.zoo.controller;

import org.cool.zoo.entities.core.Product;
import org.cool.zoo.entities.response.JResponseEntity;
import org.cool.zoo.service.ProductService;
import org.cool.zoo.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Dang Dim
 * Date     : 18-Jan-18, 1:01 PM
 * Email    : d.dim@gl-f.com
 */

@RestController
@RequestMapping(value ="/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public JResponseEntity<Object> getProducts(){
        Page<Product> products = productService.findAll(new PageRequest(0, 10));
        JResponseEntity<Object> responseEntity = ResponseFactory.build();
        if (products != null){
            responseEntity.addBody(products);
            responseEntity.setStatus(HttpStatus.OK);
            responseEntity.setMessage("SUCCESS");
        }else {
            responseEntity.setStatus(HttpStatus.NOT_FOUND);
            responseEntity.setMessage("FAIL");
        }

        return responseEntity;
    }
}
