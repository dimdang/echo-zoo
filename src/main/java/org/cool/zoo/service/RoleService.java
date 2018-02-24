package org.cool.zoo.service;

import org.cool.zoo.entities.users.Role;
import org.cool.zoo.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Created by Dang Dim
 * Date     : 27-Jan-18, 11:58 AM
 * Email    : d.dim@gl-f.com
 */

@Service
public class RoleService implements BaseServiceUtil<Role> {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Page<Role> findAll(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findOne(id);
    }

    @Override
    public Role saveOrUpdate(Role entity) {
        return roleRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        roleRepository.delete(id);
    }

    public List<Role> findAll() {
        return (List<Role>) roleRepository.findAll();
    }

    public Role findRoleByName(String name){
        return roleRepository.findRoleByName(name);
    }

    public List<Role> findByNames(String[] names) {
        return roleRepository.findAllByNameIn(names);
    }
}
