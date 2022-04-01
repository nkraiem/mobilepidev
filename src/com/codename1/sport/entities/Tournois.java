/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.sport.entities;

import java.util.Date;


public class Tournois {
    private int id;
     private String nom;
     private Date  date_debut;
     private Date  date_fin;
     private int prime;

    public Tournois() {
    }

    public Tournois(int id, String nom, Date date_debut, Date date_fin, int prime) {
        this.id = id;
        this.nom = nom;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prime = prime;
    }

    public Tournois(String nom, Date date_debut, Date date_fin, int prime) {
        this.nom = nom;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prime = prime;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public int getPrime() {
        return prime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public void setPrime(int prime) {
        this.prime = prime;
    }
     
     
}
