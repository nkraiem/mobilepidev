/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.sport.entities;


public class Equipe {
    
  
        private int id;
        private int matchs_id;
        private String nom;
        private int nbr_vic;
        private int nbr_per;
        private int nbr_null;
        private boolean suspension;

    public Equipe() {
    }

    public Equipe(int id, int matchs_id, String nom, int nbr_vic, int nbr_per, int nbr_null, boolean suspension) {
        this.id = id;
        this.matchs_id = matchs_id;
        this.nom = nom;
        this.nbr_vic = nbr_vic;
        this.nbr_per = nbr_per;
        this.nbr_null = nbr_null;
        this.suspension = suspension;
    }

    public Equipe(int matchs_id, String nom, int nbr_vic, int nbr_per, int nbr_null, boolean suspension) {
        this.matchs_id = matchs_id;
        this.nom = nom;
        this.nbr_vic = nbr_vic;
        this.nbr_per = nbr_per;
        this.nbr_null = nbr_null;
        this.suspension = suspension;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatchs_id() {
        return matchs_id;
    }

    public void setMatchs_id(int matchs_id) {
        this.matchs_id = matchs_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbr_vic() {
        return nbr_vic;
    }

    public void setNbr_vic(int nbr_vic) {
        this.nbr_vic = nbr_vic;
    }

    public int getNbr_per() {
        return nbr_per;
    }

    public void setNbr_per(int nbr_per) {
        this.nbr_per = nbr_per;
    }

    public int getNbr_null() {
        return nbr_null;
    }

    public void setNbr_null(int nbr_null) {
        this.nbr_null = nbr_null;
    }

    public boolean getSuspension() {
        return suspension;
    }

    public void setSuspension(boolean suspension) {
        this.suspension = suspension;
    }
    
        
}
