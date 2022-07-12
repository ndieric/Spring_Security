package com.example.Security.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Security.Entites.Utilisateur;
import com.example.Security.Repository.UtilisateurRepository;

@Service
public class UtilDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur user = utilisateurRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Utilisateur n est pas truver avec le nom "+username));
        return UtilDetailsImpl.build(user);
    }

    
}
