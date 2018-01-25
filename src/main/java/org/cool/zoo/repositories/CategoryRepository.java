package org.cool.zoo.repositories;

import org.cool.zoo.entities.core.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Dang Dim
 * Date     : 25-Jan-18, 5:13 PM
 * Email    : d.dim@gl-f.com
 */

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
}
