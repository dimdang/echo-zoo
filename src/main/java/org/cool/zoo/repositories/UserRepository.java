package org.cool.zoo.repositories;

import org.cool.zoo.entities.core.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Dang Dim
 * Date     : 17-Jan-18, 2:02 PM
 * Email    : d.dim@gl-f.com
 */

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
