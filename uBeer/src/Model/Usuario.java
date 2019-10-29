/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author felip
 */
public abstract class Usuario {
    protected String nome;
    protected String usuario;
    protected String senha;
    protected String telefone;
    protected String email;
    
    // Getters e Setters 

    public String getNome() {
        return nome;
    }

    public boolean setNome(String nome) {
        if(nome.length()<=100){
            this.nome=nome;
            return true;
        }
        return false;
    }

    public String getUsuario() {
        return usuario;
    }

    public boolean setUsuario(String usuario) {
        if(usuario.length()<=20){
            this.usuario=usuario;
            return true;
        }
        return false;
    }
    public String getSenha() {
        return senha;
    }

    public boolean setSenha(String senha) {
        if(senha.length()<=20){
            this.senha=senha;
            return true;
        }
        return false;
    }

    public String getTelefone() {
        return telefone;
    }

    public boolean setTelefone(String telefone) {
        if(telefone.length()<=11){
            this.telefone=telefone;
            return true;
        }
        return false;
    }

    public String getEmail() {
        return email;
    }

    public boolean setEmail(String email) {
        if(email.length()<=100){
            this.email=email;
            return true;
        }
        return false;
    }
    
    
}
