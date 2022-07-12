package com.example.Security.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Security.Entites.Utilisateur;
import com.example.Security.Repository.UtilisateurRepository;

@Service
public class UtilisateurService {

    @Autowired
    private  UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public List<Utilisateur> getAll(){
        return utilisateurRepository.findAll();
    }

    public Long newUtilisateur(Utilisateur user){
        Optional<Utilisateur> userOptional = utilisateurRepository.findByUsername(user.getUsername());
        if(userOptional.isPresent()){
            throw new IllegalStateException("Cet Utilisateur existe deja");
        }
        return utilisateurRepository.save(user).getUserId();
    }
    public void supptimerUtilisateur(Long userId){
        boolean exists = utilisateurRepository.existsById(userId);
        if(!exists){
            throw new IllegalStateException("Utilisateur de cet Id n existe pas");
        }
        utilisateurRepository.deleteById(userId);
    }
    @Transactional
    public void modifierUtilisateur(Long userId, String username,String password,String token){
        Utilisateur uti = utilisateurRepository.findById(userId).orElseThrow(() -> new IllegalStateException("Utilisaeur dont id est"+userId+"n existe pas"));
    }


    public Utilisateur ajouter(Utilisateur utilisateur){
        return utilisateurRepository.save(utilisateur);
    }

    public List<Utilisateur> touver(){
        return utilisateurRepository.findAll();
    }
    




    
}
