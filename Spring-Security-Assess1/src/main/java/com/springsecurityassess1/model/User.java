package com.springsecurityassess1.model;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String role;
    private boolean enabled;
    private int failedAttempt;
    private boolean accountUnlocked;

    public User() {
    }

    public User(Long id, String username, String password, String role,
                boolean enabled, int failedAttempt, boolean accountUnlocked) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
        this.failedAttempt = failedAttempt;
        this.accountUnlocked = accountUnlocked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int isFailedAttempt() {
        return failedAttempt;
    }

    public void setFailedAttempt(int failedAttempt) {
        this.failedAttempt = failedAttempt;
    }

    public boolean isAccountUnlocked() {
        return accountUnlocked;
    }

    public void setAccountUnlocked(boolean accountUnlocked) {
        this.accountUnlocked = accountUnlocked;
    }
}
