package org.cool.zoo.controller;

import org.cool.zoo.configure.Routes;
import org.cool.zoo.entities.response.JResponseEntity;
import org.cool.zoo.entities.users.Role;
import org.cool.zoo.repositories.RoleRepository;
import org.cool.zoo.service.RoleService;
import org.cool.zoo.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Routes.ROLE)
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(method = RequestMethod.POST)
    public JResponseEntity<Object> createRole(Role role) {
        if (role != null) {
            if (roleRepository.findRoleByName(role.getName()) != null) {
                return ResponseFactory.build("Role already exist", HttpStatus.CREATED, role.getName());
            }else {
                roleService.saveOrUpdate(role);
                return ResponseFactory.build("Role created", HttpStatus.OK, role);
            }
        }else {
            return ResponseFactory.build("Role is empty", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = Routes.NAME, method = RequestMethod.GET)
    public JResponseEntity<Role> finRoleName(@PathVariable("role") String role){
        Role rolename = null;
        if (role != null){
            rolename = roleRepository.findRoleByName(role);
            if (rolename != null) {
                return ResponseFactory.build("Success", HttpStatus.OK, rolename);
            }else {
                return ResponseFactory.build("Failed ", HttpStatus.NOT_FOUND);
            }
        }else {
            return ResponseFactory.build("Failed ", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = Routes.ID, method = RequestMethod.DELETE)
    public JResponseEntity<Role> deleteRole(@PathVariable("id") long id){
        if (id > 0){
            roleService.delete(id);
            return ResponseFactory.build("Deleted Success ", HttpStatus.OK);
        }else {
            return ResponseFactory.build("Delete Fail ", HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public JResponseEntity<Page<Role>> findAllRoles(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size
    ){
        Page<Role> allRoles = null;
        allRoles = roleService.findAll(new PageRequest(page, size));
        if (allRoles != null){
            return ResponseFactory.build("Success", HttpStatus.OK, allRoles);
        }else {
            return ResponseFactory.build("Role not exist",HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public JResponseEntity<Object> updateProduct(Role role) {
        if ( role != null && role.getId() > 0) {
            roleService.saveOrUpdate(role);
            return ResponseFactory.build("UPDATE SUCCESS", HttpStatus.OK);
        }
        return ResponseFactory.build("Please specify role you want to update..!", HttpStatus.NOT_FOUND);
    }

}
