package org.cool.zoo.controller;

import org.cool.zoo.entities.core.User;
import org.cool.zoo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Dang Dim
 * Date     : 17-Jan-18, 1:33 PM
 * Email    : d.dim@gl-f.com
 */

public class StudentController {

    @Autowired
    private UserService userService;


    void test(){
        
        userService.saveOrUpdate(new User());
    }
}
