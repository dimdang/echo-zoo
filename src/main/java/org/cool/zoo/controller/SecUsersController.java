package org.cool.zoo.controller;

import org.cool.zoo.configure.Routes;
import org.cool.zoo.entities.response.JResponseEntity;
import org.cool.zoo.entities.users.Role;
import org.cool.zoo.entities.users.User;
import org.cool.zoo.repositories.RoleRepository;
import org.cool.zoo.repositories.UserRepository;
import org.cool.zoo.security.Authorities;
import org.cool.zoo.security.UserAlreadyRegisterException;
import org.cool.zoo.service.RoleService;
import org.cool.zoo.service.SecUserService;
import org.cool.zoo.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by Dang Dim
 * Date     : 17-Jan-18, 1:33 PM
 * Email    : d.dim@gl-f.com
 */

@RestController
@RequestMapping(Routes.SECURE + Routes.USER)
public class SecUsersController {

    @Autowired
    private SecUserService secUserService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = Routes.EMAIL, method = RequestMethod.GET)
    public JResponseEntity<User> findByEmail(@PathVariable(value = "email") String email) {
        if (email != null) {
            User usersFromEmail = userRepository.findByEmail(email);
            if (usersFromEmail != null) {
                return ResponseFactory.build("SUCCESS", HttpStatus.OK, usersFromEmail);
            } else {
                return ResponseFactory.build("USERS NOT FOUND", HttpStatus.NO_CONTENT);
            }
        } else {
            return ResponseFactory.build("Please make sure you what do you want", HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(method = RequestMethod.GET)
    public JResponseEntity<Page<User>> allUsers(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size
    ) {
        Page<User> allUser = secUserService.findAll(new PageRequest(page, size));
        if (allUser != null) {
            return ResponseFactory.build("SUCCESS", HttpStatus.OK, allUser);
        } else
            return ResponseFactory.build("USERS NOT FOUND", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = Routes.ID, method = RequestMethod.GET)
    public JResponseEntity<User> findUserById(@PathVariable(value = "id") long id) {
        if (id > 0) {
            User oneUser = secUserService.findById(id);
            return ResponseFactory.build("SUCCESSFUL", HttpStatus.OK, oneUser);
        } else
            return ResponseFactory.build("FAILED", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.POST)
    public JResponseEntity<Object> createUser(User user) {
        if (user != null && user.getUsername() != null) {
            User loadUserLogin = userRepository.findByUsername(user.getUsername());
            if (loadUserLogin != null) {
                return ResponseFactory.build("User already exist", HttpStatus.CREATED, user.getUsername());
            } else {
                secUserService.saveOrUpdate(user);
                return ResponseFactory.build("REGISTER SUCCESS", HttpStatus.NOT_FOUND);
            }
        }
        return ResponseFactory.build("NOT ENOUGH DATA", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = Routes.ROLE, method = RequestMethod.GET)
    public JResponseEntity<Page<User>> findUserRole(@PathVariable(value = "authorities") String authorities,
                                                    @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                    @RequestParam(value = "size", defaultValue = "10", required = false) int size
    ) {
        Role authority = roleRepository.findRoleByName(authorities);
        Page<User> userInRole = null;
        if (authority != null) {
            userInRole = userRepository.findAllByAuthoritiesEquals(authority, new PageRequest(page, size));
        }
        if (userInRole != null) {
            return ResponseFactory.build("SUCCESS", HttpStatus.OK, userInRole);
        } else
            return ResponseFactory.build("NO USER AVAILABLE", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = Routes.ID, method = RequestMethod.PUT)
    public JResponseEntity<Object> updateUser(User user) {
        if (user != null && user.getId() != null && user.getUsername() != null) {
            User loadUserLogin = userRepository.findByUsername(user.getUsername());
            if (loadUserLogin != null) {
                return ResponseFactory.build("Update fail, user already exist", HttpStatus.CREATED, user.getUsername());
            } else {
                String pwd = userRepository.existsByPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                if (pwd != null) {
                    secUserService.saveOrUpdate(user);
                    return ResponseFactory.build("UPDATE SUCCESS", HttpStatus.OK);
                } else {
                    return ResponseFactory.build("Password is not match.. !", HttpStatus.NOT_FOUND);
                }
            }
        }
        return ResponseFactory.build("Please specify user you want to update..!", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = Routes.PASSWORDS + Routes.ID, method = RequestMethod.PUT)
    public JResponseEntity<User> resetPasswords(User user) {
        if (user != null && user.getPassword() != null) {
            String pwd = userRepository.existsByPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            if (pwd != null){
                return ResponseFactory.build("Please choose different from a previous..!");
            }else {
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                secUserService.saveOrUpdate(user);
                return ResponseFactory.build("Your password already changed.!");
            }
        }else {
            return ResponseFactory.build("Please enter your new passwords...!");
        }
    }

    @RequestMapping(value = Routes.ROLE + Routes.ID, method = RequestMethod.PUT)
    public JResponseEntity<User> assignRolesToUsers(User user, List<Role> roles) {
        User upgradeUser = null;
        if (user != null && !roles.isEmpty()) {
            upgradeUser = secUserService.assignAuthorities(user, roles);
            return ResponseFactory.build("User is already assign role", HttpStatus.OK, upgradeUser);
        } else {
            return ResponseFactory.build("Role is not specify", HttpStatus.OK);
        }
    }

}
