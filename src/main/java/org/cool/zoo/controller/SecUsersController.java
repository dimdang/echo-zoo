package org.cool.zoo.controller;

import org.cool.zoo.configure.Routes;
import org.cool.zoo.entities.response.JResponseEntity;
import org.cool.zoo.entities.users.Role;
import org.cool.zoo.entities.users.RoleNames;
import org.cool.zoo.entities.users.User;
import org.cool.zoo.service.RoleService;
import org.cool.zoo.service.SecUserService;
import org.cool.zoo.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

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
    private RoleService roleService;

    @RequestMapping(value = Routes.EMAIL, method = RequestMethod.GET)
    public JResponseEntity<User> findByEmail(@PathVariable(value = "email") String email) {
        User userEmail = secUserService.findByEmail(email);
        if (userEmail != null) {
            return ResponseFactory.build("SUCCESS", HttpStatus.OK, userEmail);
        } else {
            return ResponseFactory.build("USERS NOT FOUND", HttpStatus.NO_CONTENT);
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
            User loadUserLogin = secUserService.findByUsername(user.getUsername());
            if (loadUserLogin != null) {
                return ResponseFactory.build("User already exist", HttpStatus.FORBIDDEN, user.getUsername());
            } else {
                secUserService.saveOrUpdate(user);
                return ResponseFactory.build("REGISTER SUCCESS", HttpStatus.OK);
            }
        }
        return ResponseFactory.build("NOT ENOUGH DATA", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = Routes.ROLE, method = RequestMethod.GET)
    public JResponseEntity<Page<User>> findUserRole(@PathVariable(value = "authorities") String authorities,
                                                    @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                    @RequestParam(value = "size", defaultValue = "10", required = false) int size
    ) {
        Role authority = roleService.findRoleByName(authorities);
        Page<User> userInRole = null;
        if (authority != null) {
            userInRole = secUserService.findAllByAuthoritiesEquals(authority, new PageRequest(page, size));
        }
        if (userInRole != null) {
            return ResponseFactory.build("SUCCESS", HttpStatus.OK, userInRole);
        } else
            return ResponseFactory.build("NO USER AVAILABLE", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = Routes.PASSWORDS + Routes.ID, method = RequestMethod.PUT)
    public JResponseEntity<User> resetPasswords(@PathVariable(value = "id") Long id, User user) {
        if (findUser(id) != null) {
            String pwd = secUserService.existsByPassword(user.getPassword());
            if (pwd != null) {
                return ResponseFactory.build("It's your old passwords.");
            } else {
                secUserService.saveOrUpdate(user);
                return ResponseFactory.build("Your password already changed.!");
            }
        } else {
            return ResponseFactory.build("User Not Found", HttpStatus.NOT_FOUND);
        }
    }




    @RequestMapping(value = Routes.ID, method = RequestMethod.PUT)
    public JResponseEntity<Object> updateUser(
            @PathVariable(value = "id") Long id,
            //@RequestParam(value = "roleNames1") String[] roleNames1,
            @RequestBody RoleNames roleNames
            ) {

        User user = findUser(id);
        List<Role> roles = roleService.findByNames(roleNames.getNames());

        if (user != null && !roles.isEmpty()) {
            user.setAuthorities(new HashSet<>(roles));
            secUserService.saveOrUpdate(user);
            return ResponseFactory.build("UPDATE SUCCESS", HttpStatus.OK, user);
        } else {
            return ResponseFactory.build("UPDATE FAILED", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = Routes.ID, method = RequestMethod.DELETE)
    public JResponseEntity<User> deleteUser(@PathVariable(value = "id") Long id) {
        if (findUser(id) == null)
            return ResponseFactory.build("Delete Failed", HttpStatus.NOT_FOUND);

        secUserService.delete(id);
        return ResponseFactory.build("Deleted");
    }

    private User findUser(Long id) {
        User exist = null;
        if (id != null) {
            exist = secUserService.findById(id);
        }
        return exist;
    }

}
