package br.dcc.ufba.mata63.balaiolivros.backend.models;

import java.io.Serializable;
import java.util.Objects;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author jeferson
 */
public class UsuarioModel implements Serializable {

    private Long id = null;
    private String nome;
    private String username;
    private String senha;
    private String email;

    public UsuarioModel(String nome, String username, String senha, String email) {
        this.nome = nome;
        this.username = username;
        this.senha = BCrypt.hashpw(senha, BCrypt.gensalt());;
        this.email = email;
    }    
    
    public UsuarioModel(UsuarioModel other) {
        Objects.requireNonNull(other);
        this.id = other.getId();
        this.nome = other.getNome();
        this.senha = other.senha; // THIS IS VERY DANGEROUS!
        this.username = other.getUsername();
        this.email = other.getEmail();
    }

    /**
     * 
     * Autentica o usuario com a plataforma.
     * 
     * @param senha Senha a ser verificada
     * @return Resultado da autenticação.
     */
    public boolean authenticate(String senha){
        return BCrypt.checkpw(senha, this.senha);
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 
     * Modifica a senha do usuario em questão
     * 
     * @param senhaAtual Senha atual do usuario
     * @param senhaNova Senha a ser modificada
     * @return Retorna se foi possível atualizar a senha
     */
    public boolean setSenha(String senhaAtual, String senhaNova) {
        if(authenticate(senhaAtual)){
            this.senha = senhaNova;
            return true;
        }
        return false;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    private String getSenha(String senhaAtual){
        return BCrypt.checkpw(senhaAtual, this.senha) ? this.senha : "";
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
        final UsuarioModel other = (UsuarioModel) obj;
        return Objects.equals(this.id, other.id);
    }
}
