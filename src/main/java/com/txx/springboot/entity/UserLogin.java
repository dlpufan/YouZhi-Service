package com.txx.springboot.entity;

/**
 * @author 淘米水浇花
 */
public class UserLogin {
    private int id;
    private String username;
    private String password;
    private String token;
    private Integer identity;

    public UserLogin() {

    }

    public UserLogin(int id, String username, String password, String token, Integer identity) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.token = token;
        this.identity = identity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    @Override
    public String toString() {
        return "StudentLogin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", identity=" + identity +
                '}';
    }
}