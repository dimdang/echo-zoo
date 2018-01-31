package org.cool.zoo.controller;

import org.cool.zoo.configure.Routes;
import org.cool.zoo.entities.response.JResponseEntity;
import org.cool.zoo.entities.users.Role;
import org.cool.zoo.entities.users.User;
import org.cool.zoo.repositories.RoleRepository;
import org.cool.zoo.repositories.UserRepository;
import org.cool.zoo.security.UserAlreadyRegisterException;
import org.cool.zoo.service.RoleService;
import org.cool.zoo.service.SecUserService;
import org.cool.zoo.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Dang Dim
 * Date     : 17-Jan-18, 1:33 PM
 * Email    : d.dim@gl-f.com
 */

@RestController
public class SecUsersController {

    @Autowired
    SecUserService secUserService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = Routes.SECURE_USER_EMAIL, method = RequestMethod.GET)
    public JResponseEntity<User> findByEmail(@PathVariable(value = "email") String email) {
        if (email != null){
            User usersFromEmail = userRepository.findByEmail(email);
            if (usersFromEmail != null){
                return ResponseFactory.build("SUCCESS", HttpStatus.OK, usersFromEmail);
            }else {
                return ResponseFactory.build("USERS NOT FOUND", HttpStatus.NO_CONTENT);
            }
        }else {
            return ResponseFactory.build("Please make sure you what do you want", HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = Routes.SECURE_USER, method = RequestMethod.GET)
    public JResponseEntity<Page<User>> allUsers() {
        Page<User> allUser = secUserService.findAll(new PageRequest(0, 10));
        if (allUser != null) {
            return ResponseFactory.build("SUCCESS", HttpStatus.OK, allUser);
        } else
            return ResponseFactory.build("USERS NOT FOUND", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = Routes.SECURE_USER_ID, method = RequestMethod.GET)
    public JResponseEntity<User> findUserById(@PathVariable(value = "id") long id) {
        if (id > 0) {
            User oneUser = secUserService.findById(id);
            return ResponseFactory.build("SUCCESSFUL", HttpStatus.OK, oneUser);
        } else
            return ResponseFactory.build("FAILED", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = Routes.SECURE_USER, method = RequestMethod.POST)
    public JResponseEntity<Object> createUser(User user) {
        if (user != null && user.getUsername() != null) {
            User loadUserLogin = userRepository.findByUsername(user.getUsername());
            if (loadUserLogin != null) {
                return ResponseFactory.build("User already exist", HttpStatus.CREATED, user.getUsername());
            }else {
                secUserService.saveOrUpdate(user);
                return ResponseFactory.build("REGISTER SUCCESS", HttpStatus.NOT_FOUND);
            }
        }
        return ResponseFactory.build("NOT ENOUGH DATA", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = Routes.SECURE_USER + Routes.ROLE_NAME, method = RequestMethod.GET)
    public JResponseEntity<Page<User>> findUserRole(@PathVariable(value = "authorities") String authorities){
        Role authority = roleRepository.findRoleByName(authorities);
        Page<User> userInRole = null;
        if (authority != null){
            userInRole = userRepository.findAllByAuthoritiesEquals(authority, new PageRequest(0,10));
        }
        if (userInRole != null){
            return ResponseFactory.build("SUCCESS", HttpStatus.OK, userInRole);
        }else
            return ResponseFactory.build("NO USER AVAILABLE", HttpStatus.NOT_FOUND);
    }

}
