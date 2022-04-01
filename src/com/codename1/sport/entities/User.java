/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.sport.entities;


public class User {
 private int id;
 private String email;
 private String password;
 private String name;
 private String lastName;
 private String image;
 private String activation_token;
 private String reset_token;
 private String roles ;

    public User(int id, String email, String password, String name, String lastName, String image, String activation_token, String reset_token, String roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.image = image;
        this.activation_token = activation_token;
        this.reset_token = reset_token;
        this.roles = roles;
    }
    
    public User(String email, String password, String name, String lastName, String image, String activation_token, String reset_token, String roles) {
        
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.image = image;
        this.activation_token = activation_token;
        this.reset_token = reset_token;
        this.roles = roles;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getActivation_token() {
        return activation_token;
    }

    public void setActivation_token(String activation_token) {
        this.activation_token = activation_token;
    }

    public String getReset_token() {
        return reset_token;
    }

    public void setReset_token(String reset_token) {
        this.reset_token = reset_token;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
 
    
 
}
