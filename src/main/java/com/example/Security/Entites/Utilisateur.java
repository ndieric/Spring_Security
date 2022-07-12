package com.example.Security.Entites;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "utilisateurs",
    uniqueConstraints = {
        @UniqueConstraint(columnNames ="password")
    }
)
public class Utilisateur {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "User_Roles",
        joinColumns = @JoinColumn(name="userId"),
        inverseJoinColumns = @JoinColumn(name="idRole")
    )
    private Set<Role> roles = new HashSet<>();

    public Utilisateur() {
    }

    public Utilisateur(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Utilisateur [password=" + password + ", userId=" + userId + ", username=" + username + "]";
    }

   

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Role> getRoles() {
        return roles;
    }
    

    
    

    
    
}
