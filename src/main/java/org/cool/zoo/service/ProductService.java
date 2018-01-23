package org.cool.zoo.service;

import org.cool.zoo.entities.core.Product;
import org.cool.zoo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by Dang Dim
 * Date     : 18-Jan-18, 12:47 PM
 * Email    : d.dim@gl-f.com
 */

@Service
public class ProductService implements BaseServiceUtil<Product> {


    @Autowired
    private  ProductRepository  productRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public Product saveOrUpdate(Product entity) {
        return productRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(id);
    }
}
