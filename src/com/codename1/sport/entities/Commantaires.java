/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.sport.entities;

import java.util.Date;


public class Commantaires {
        private int id;
        private int publication_id;
        private String contenu;
        private Date date_commentaire;

    public Commantaires(int id, int publication_id, String contenu, Date date_commentaire) {
        this.id = id;
        this.publication_id = publication_id;
        this.contenu = contenu;
        this.date_commentaire = date_commentaire;
    }

    public Commantaires(int publication_id, String contenu, Date date_commentaire) {
        this.publication_id = publication_id;
        this.contenu = contenu;
        this.date_commentaire = date_commentaire;
    }

    public Commantaires() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPublication_id() {
        return publication_id;
    }

    public void setPublication_id(int publication_id) {
        this.publication_id = publication_id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDate_commentaire() {
        return date_commentaire;
    }

    public void setDate_commentaire(Date date_commentaire) {
        this.date_commentaire = date_commentaire;
    }
        
        
        
}
