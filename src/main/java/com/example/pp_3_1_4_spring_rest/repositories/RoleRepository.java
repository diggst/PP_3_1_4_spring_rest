package com.example.pp_3_1_4_spring_rest.repositories;

import com.example.pp_3_1_4_spring_rest.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}