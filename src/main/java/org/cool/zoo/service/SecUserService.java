package org.cool.zoo.service;

import org.cool.zoo.entities.users.Role;
import org.cool.zoo.entities.users.User;
import org.cool.zoo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Dang Dim
 * Date     : 18-Jan-18, 2:05 PM
 * Email    : d.dim@gl-f.com
 */

@Service
public class SecUserService implements BaseServiceUtil<User> {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
    public User saveOrUpdate(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    public User assignAuthorities(User user, List<Role> roles) {
        if (user != null && !roles.isEmpty()) {
            Set<Role> roleSet = new HashSet<>();
            roleSet.addAll(roles);
            user.setAuthorities(roleSet);
            this.saveOrUpdate(user);
        }
        return user;
    }

    public boolean validatePassword(String pwd){
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        if (pwd.matches(pattern))
            return true;
        return false;
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Page<User> findAllByAuthoritiesEquals(Role authority, PageRequest pageRequest) {
        return userRepository.findAllByAuthoritiesEquals(authority, pageRequest);
    }

    public String existsByPassword(String encode) {
        return userRepository.existsByPassword(bCryptPasswordEncoder.encode(encode));
    }
}
