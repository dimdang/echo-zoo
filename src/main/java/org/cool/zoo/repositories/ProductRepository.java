package org.cool.zoo.repositories;

import org.cool.zoo.entities.core.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Dang Dim
 * Date     : 18-Jan-18, 12:48 PM
 * Email    : d.dim@gl-f.com
 */

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {
}
