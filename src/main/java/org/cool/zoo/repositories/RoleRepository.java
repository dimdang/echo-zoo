package org.cool.zoo.repositories;

import org.cool.zoo.entities.users.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Dang Dim
 * Date     : 27-Jan-18, 11:37 AM
 * Email    : d.dim@gl-f.com
 */

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {

    Role findRoleByName(String name);

    List<Role> findAllByNameIn(String[] names);

}
