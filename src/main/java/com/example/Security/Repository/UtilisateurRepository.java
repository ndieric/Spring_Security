package com.example.Security.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Security.Entites.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
    Optional<Utilisateur> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByPassword(String password);
    
}
