package org.cool.zoo.controller;

import org.cool.zoo.configure.Routes;
import org.cool.zoo.entities.response.JResponseEntity;
import org.cool.zoo.entities.users.Role;
import org.cool.zoo.entities.users.User;
import org.cool.zoo.repositories.RoleRepository;
import org.cool.zoo.repositories.UserRepository;
import org.cool.zoo.service.RoleService;
import org.cool.zoo.service.SecUserService;
import org.cool.zoo.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = Routes.SECURE_USER, method = RequestMethod.GET)
    public JResponseEntity<Object> allUsers() {
        Page<User> allUser = secUserService.findAll(new PageRequest(0, 10));
        if (allUser != null) {
            return ResponseFactory.build("SUCCESS", HttpStatus.OK, allUser);
        } else
            return ResponseFactory.build("USERS NOT FOUND", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = Routes.SECURE_USER_ID, method = RequestMethod.GET)
    public JResponseEntity<Object> findUserById(@PathVariable(value = "id") long id) {
        if (id > 0) {
            User oneUser = secUserService.findById(id);
            return ResponseFactory.build("SUCCESSFUL", HttpStatus.OK, oneUser);
        } else
            return ResponseFactory.build("FAILED", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = Routes.SECURE_USER, method = RequestMethod.POST)
    public JResponseEntity<Object> createUser(User user) {
        User loadUserLogin = userRepository.findByUsername(user.getUsername());
        if (loadUserLogin != null) {
            Role roles = roleService.findById(user.getAuthorities());
            if (roles != null) {
            }

        } else {
            return ResponseFactory.build("NO PRODUCT TO ADD", HttpStatus.NOT_FOUND);
        }
        return ResponseFactory.build("SOME THING WENT WRONG PLEASE CONTRACT BUCKY DEV", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = Routes.PRODUCT_ID, method = RequestMethod.DELETE)
    public JResponseEntity<Object> deleteProduct(@PathVariable(value = "id") long id) {
        if (id > 0) {
            productService.delete(id);
            return ResponseFactory.build("DELETED SUCCESS", HttpStatus.OK);
        } else
            return ResponseFactory.build("DELETED FAILED", HttpStatus.NOT_FOUND);
    }


    @RequestMapping(value = Routes.PRODUCT_CATEGORY_NAME, method = RequestMethod.GET)
    public JResponseEntity<Object> findProductByCategory(@RequestParam("id") Long id) {
        Page<Product> productCategory = productRepository.findProductByCategory_Id(id, new PageRequest(0,10));
        if (productCategory != null){
            return ResponseFactory.build("OK", HttpStatus.OK, productCategory);
        }else
            return ResponseFactory.build("FAILED", HttpStatus.NOT_FOUND);
    }

}
