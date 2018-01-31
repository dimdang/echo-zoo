package org.cool.zoo.repositories;

import org.cool.zoo.entities.core.Category;
import org.cool.zoo.entities.core.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Dang Dim
 * Date     : 18-Jan-18, 12:48 PM
 * Email    : d.dim@gl-f.com
 */

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Long>, JpaSpecificationExecutor<Category> {

    Page<Product> findAllByCategoryEquals(@Param(value = "category") Category category, Pageable page);
}
