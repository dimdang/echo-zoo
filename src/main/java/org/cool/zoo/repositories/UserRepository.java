package org.cool.zoo.repositories;

import org.cool.zoo.entities.core.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Dang Dim
 * Date     : 17-Jan-18, 2:02 PM
 * Email    : d.dim@gl-f.com
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User createUser(User user);

    void updateUser(User id);

    User deleteUser(User id);
}
