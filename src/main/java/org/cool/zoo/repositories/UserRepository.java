package org.cool.zoo.repositories;

import org.cool.zoo.entities.users.Role;
import org.cool.zoo.entities.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by Dang Dim
 * Date     : 17-Jan-18, 2:02 PM
 * Email    : d.dim@gl-f.com
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(@Param("email") String email);

    Page<User> findAllByAuthoritiesEquals(@Param("authorities")Role authority, Pageable pageable);

}
