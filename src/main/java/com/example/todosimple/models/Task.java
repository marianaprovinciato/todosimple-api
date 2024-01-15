package com.example.todosimple.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;
    @Column(name = "description", nullable = false, length = 300)
    @NotNull
    @Size(min = 1, max = 300)
    @NotEmpty
    private String description;

    public Task() {

    }

    public Task(Long id,User user,String description) {
        this.id = id;
        this.user = user;
        this.description = description;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Task other = (Task) obj;
        if (id == null) {
            return other.id == null;
        } else {
            return id.equals(other.id) &&
                    Objects.equals(user, other.user) &&
                    Objects.equals(description, other.description);
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
