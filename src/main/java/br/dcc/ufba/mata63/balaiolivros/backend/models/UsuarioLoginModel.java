/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.dcc.ufba.mata63.balaiolivros.backend.models;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author jeferson
 */
public class UsuarioLoginModel implements Serializable {

    private Long id = null;
    private String username;
    private String senha;

    public UsuarioLoginModel(String username, String senha) {
        this.username = username;
        this.senha = senha;
    }    
    
    public UsuarioLoginModel(UsuarioLoginModel other) {
        Objects.requireNonNull(other);
        this.id = other.getId();
        this.senha = other.getSenha();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsuarioLoginModel other = (UsuarioLoginModel) obj;
        return Objects.equals(this.id, other.id);
    }
}
