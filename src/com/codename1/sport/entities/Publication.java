/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.sport.entities;

import java.util.Date;


public class Publication {
     private int id;
     private String titre_pub;
     private String contenu_pub;
     private Date  datePub;
     private String autheurPub;
     private String imagePub;

    public Publication(int id, String titre_pub, String contenu_pub, Date datePub, String autheurPub, String imagePub) {
        this.id = id;
        this.titre_pub = titre_pub;
        this.contenu_pub = contenu_pub;
        this.datePub = datePub;
        this.autheurPub = autheurPub;
        this.imagePub = imagePub;
    }

    public Publication() {
    }

    public Publication(String titre_pub, String contenu_pub, Date datePub, String autheurPub, String imagePub) {
        this.titre_pub = titre_pub;
        this.contenu_pub = contenu_pub;
        this.datePub = datePub;
        this.autheurPub = autheurPub;
        this.imagePub = imagePub;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre_pub() {
        return titre_pub;
    }

    public void setTitre_pub(String titre_pub) {
        this.titre_pub = titre_pub;
    }

    public String getContenu_pub() {
        return contenu_pub;
    }

    public void setContenu_pub(String contenu_pub) {
        this.contenu_pub = contenu_pub;
    }

    public Date getDatePub() {
        return datePub;
    }

    public void setDatePub(Date datePub) {
        this.datePub = datePub;
    }

    public String getAutheurPub() {
        return autheurPub;
    }

    public void setAutheurPub(String autheurPub) {
        this.autheurPub = autheurPub;
    }

    public String getImagePub() {
        return imagePub;
    }

    public void setImagePub(String imagePub) {
        this.imagePub = imagePub;
    }
     
     
     
}
