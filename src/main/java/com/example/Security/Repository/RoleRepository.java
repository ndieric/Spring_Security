package com.example.Security.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Security.Entites.ERole;
import com.example.Security.Entites.Role;
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
  
    
}
