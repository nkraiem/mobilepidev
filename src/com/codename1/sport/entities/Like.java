/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.sport.entities;


public class Like {
  
    private int id;
    private int publicationid;
    private int rate;

    public Like(int id, int publicationid, int rate) {
        this.id = id;
        this.publicationid = publicationid;
        this.rate = rate;
    }

    public Like(int publicationid, int rate) {
        this.publicationid = publicationid;
        this.rate = rate;
    }

    public Like() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPublicationid() {
        return publicationid;
    }

    public void setPublicationid(int publicationid) {
        this.publicationid = publicationid;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
    
    
}
