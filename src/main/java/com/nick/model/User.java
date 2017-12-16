package com.nick.model;


@Table("Users")
public class User {
    //annotation fields for each
    @ID("ID")
    private Long id;
    @Column("NAME")
    private String name;
    @Column("EMAIL")
    private String email;
    @Column("PASSWORD")
    private String password;
    private String token;

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
//    public User(String name, String email, String password) {
//        this.name = name;
//        this.email = email;
//        this.password = password;
//    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setPassword(String password) {
        this.password = password;

    }

}

