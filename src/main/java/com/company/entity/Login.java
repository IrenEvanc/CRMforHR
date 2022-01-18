package com.company.entity;

import javax.persistence.*;

@Entity
@Table(name = "login")
public class Login {
    @EmbeddedId
    private LoginId id;

    @OneToOne(optional = false)
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "roleid")
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LoginId getId() {
        return id;
    }

    public void setId(LoginId id) {
        this.id = id;
    }
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Login login1 = (Login) o;
//        return Objects.equals(login, login1.login) && Objects.equals(userpassword, login1.userpassword);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(login, userpassword);
//    }
}