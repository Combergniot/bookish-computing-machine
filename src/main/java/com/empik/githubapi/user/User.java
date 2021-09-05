package com.empik.githubapi.user;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String login;


    private int request_count;


    public User() {
    }

    public User(String login, int request_count) {
        this.login = login;
        this.request_count = request_count;
    }

    public String getLogin() {
        return login;
    }

    public int getRequest_count() {
        return request_count;
    }

    public void modifyRequest_count(int request_count) {
        this.request_count = request_count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return request_count == user.request_count && Objects.equals(id, user.id) && Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, request_count);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", request_count=" + request_count +
                '}';
    }
}
