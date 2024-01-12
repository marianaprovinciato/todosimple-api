package com.example.todosimple.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "\"usuario\"")
public class User {
    public interface CreateUser{}
    public interface UpdateUser{}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;
    @Column(name = "usuario",length = 100,nullable = false,unique = true)
    @NotNull(groups = CreateUser.class) //valor nulo
    @NotEmpty(groups = CreateUser.class) //string vazia
    @Size(groups = CreateUser.class, min = 2,max = 50)
    private String usuario;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)// garante que a senha seja so de escrita
    @Column(name = "senha",length = 40,nullable = false)
    @NotNull(groups = {CreateUser.class, UpdateUser.class})
    @NotEmpty(groups = {CreateUser.class, UpdateUser.class})
    @Size(groups = {CreateUser.class, UpdateUser.class}, min = 8,max = 30)
    private String senha;


    public User() {
    }

    public User(Long id, String usuario, String senha) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            return other.id == null;
        } else {
            return id.equals(other.id) &&
                    Objects.equals(usuario, other.usuario) &&
                    Objects.equals(senha, other.senha);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime + result +((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }
}
