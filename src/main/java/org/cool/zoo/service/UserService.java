package org.cool.zoo.service;

import org.cool.zoo.entities.core.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by Dang Dim
 * Date     : 18-Jan-18, 2:05 PM
 * Email    : d.dim@gl-f.com
 */
@Service
public class UserService implements BaseServiceUtil<User>{
    @Override
    public Page<User> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public User saveOrUpdate(User entity) {
        return null;
    }

    @Override
    public User delete(User entity) {
        return null;
    }



}
