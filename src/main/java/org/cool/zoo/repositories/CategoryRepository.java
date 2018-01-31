package org.cool.zoo.repositories;

import org.cool.zoo.entities.core.Category;
import org.cool.zoo.entities.users.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

/**
 * Created by Dang Dim
 * Date     : 25-Jan-18, 5:13 PM
 * Email    : d.dim@gl-f.com
 */

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {

    Category findByCategoryName(String name);
}
