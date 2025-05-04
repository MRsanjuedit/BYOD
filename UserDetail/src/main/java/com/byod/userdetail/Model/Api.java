package com.byod.userdetail.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.lang.String;
import java.time.LocalDateTime;


@Entity
@Table(name = "apis")
public class Api {

    @Id
    @GeneratedValue
    @Column(name = "apiId")
    private String id;

    @Column(name = "api_name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false , columnDefinition = "VARCHAR")  // Foreign key
    @JsonBackReference
    private User user;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;


    // Getters and setters...


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    @Transient
    public boolean isExpired() {
        return expiresAt != null && expiresAt.isBefore(LocalDateTime.now());
    }
}
