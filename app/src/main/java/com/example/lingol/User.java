package com.example.lingol;

public class User {
    private String id;
    private String name;
    private String email;
    private String language;

    // Construtor vazio necessário para Firebase
    public User() {
    }

    // Construtor personalizado
    public User(String id, String name, String email, String language) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.language = language;
    }

    // Getters e Setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
