/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.sport.entities;


public class Joueur {
      private int id;
        private int equipes_id;
        private String nom;
        private String prenom;
        private String email;
        private int numero;
        private int nbr_partie_jouer;

    public Joueur( int equipes_id, String nom, String prenom, String email, int numero) {
        
        this.equipes_id = equipes_id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numero = numero;
    }
        public Joueur(){}

    public Joueur(int id, int equipes_id, String nom, String prenom, String email, int numero) {
        this.id = id;
        this.equipes_id = equipes_id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numero = numero;
    }
        
        

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEquipes_id() {
        return equipes_id;
    }

    public void setEquipes_id(int equipes_id) {
        this.equipes_id = equipes_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNbr_partie_jouer() {
        return nbr_partie_jouer;
    }

    public void setNbr_partie_jouer(int nbr_partie_jouer) {
        this.nbr_partie_jouer = nbr_partie_jouer;
    }
        
        
        
}
