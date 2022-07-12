package com.example.Security.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Security.Entites.Utilisateur;
import com.example.Security.Service.UtilisateurService;

@Controller
@RequestMapping(value = "/api")
public class controller {
    @Autowired
    UtilisateurService utilisateurService;

    @PostMapping("/ajout")
    public Long ajout(Utilisateur user){
        return utilisateurService.newUtilisateur(user);
    }

    @PostMapping("/insert")
    public Utilisateur inserer(@RequestBody Utilisateur utilisateur){
        return utilisateurService.ajouter(utilisateur);
    }

    @GetMapping("/trouver")
    public List<Utilisateur> amener(){
        return utilisateurService.touver();
        
    }

    
    
}
