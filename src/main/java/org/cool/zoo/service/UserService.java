package org.cool.zoo.service;

import org.cool.zoo.entities.core.User;
import org.cool.zoo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by Dang Dim
 * Date     : 18-Jan-18, 2:05 PM
 * Email    : d.dim@gl-f.com
 */
@Service
public class UserService implements BaseServiceUtil<User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User saveOrUpdate(User entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
