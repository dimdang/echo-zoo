package org.cool.zoo.controller;

import org.cool.zoo.configure.Routes;
import org.cool.zoo.entities.core.Product;
import org.cool.zoo.entities.response.JResponseEntity;
import org.cool.zoo.service.ProductService;
import org.cool.zoo.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    JResponseEntity<Object> responseEntity = null;

    @ResponseBody
    @RequestMapping(value = Routes.PRODUCT, method = RequestMethod.GET)
    public JResponseEntity<Object> getProducts() {
        Page<Product> products = productService.findAll(new PageRequest(0, 10));
        responseEntity = ResponseFactory.build();
        if (products != null) {
            responseEntity.addBody(products);
            responseEntity.setStatus(HttpStatus.OK);
            responseEntity.setMessage("SUCCESSFUL");
        } else {
            responseEntity.setStatus(HttpStatus.NOT_FOUND);
            responseEntity.setMessage("FAILED");
        }

        return responseEntity;
    }

    @RequestMapping(value = Routes.PRODUCT + Routes.API_ID, method = RequestMethod.GET)
    public JResponseEntity<Object> findProductById(@PathVariable(value = "id") long id) {
        Product product = productService.findById(id);
        responseEntity = ResponseFactory.build();
        if (product != null){
            responseEntity.addBody(product);
            responseEntity.setStatus(HttpStatus.OK);
            responseEntity.setMessage("SUCCESS");
        } else {
            responseEntity.setStatus(HttpStatus.NOT_FOUND);
            responseEntity.setMessage("FAILED");
        }
        return responseEntity;
    }

    public JResponseEntity<Object> saveProducts(Product product) {
        if (product != null){
            productService.saveOrUpdate(product);
        }
        if (productService.saveOrUpdate(product) != null){

        }
    }
}
