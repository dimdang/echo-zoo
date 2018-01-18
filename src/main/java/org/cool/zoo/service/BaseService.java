package org.cool.zoo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Dang Dim
 * Date     : 18-Jan-18, 12:48 PM
 * Email    : d.dim@gl-f.com
 */

public interface BaseService<T> {

    Page<T> findAll(Pageable pageable);

    T findById(Long id);

    T saveOrUpdate(T entity);

    T delete(T entity);
}

