package com.example.pp_3_1_4_spring_rest.services;

import com.example.pp_3_1_4_spring_rest.model.Role;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoleService {

    @Transactional(readOnly = true)
    List<Role> listRoles();
}
