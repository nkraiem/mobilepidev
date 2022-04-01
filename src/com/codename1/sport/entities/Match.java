/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.sport.entities;

import java.util.Date;


public class Match {
  private int id;
private int tournoi_id;
private int equipe1_id ;
private int equipe2_id;
private Date date_match;
private String 	ref_match ;
private int score_a;
private int score_b;
private String 	equipe1_nom;
private String 	equipe2_nom;

    public Match(int tournoi_id, int equipe1_id, int equipe2_id, Date date_match, String ref_match) {
        this.tournoi_id = tournoi_id;
        this.equipe1_id = equipe1_id;
        this.equipe2_id = equipe2_id;
        this.date_match = date_match;
        this.ref_match = ref_match;
    }

    public Match(int id, int tournoi_id, int equipe1_id, int equipe2_id, Date date_match, String ref_match, int score_a, int score_b) {
        this.id = id;
        this.tournoi_id = tournoi_id;
        this.equipe1_id = equipe1_id;
        this.equipe2_id = equipe2_id;
        this.date_match = date_match;
        this.ref_match = ref_match;
        this.score_a = score_a;
        this.score_b = score_b;
    }

    public Match() {
    }

    public String getEquipe1_nom() {
        return equipe1_nom;
    }

    public void setEquipe1_nom(String equipe1_nom) {
        this.equipe1_nom = equipe1_nom;
    }

    public String getEquipe2_nom() {
        return equipe2_nom;
    }

    public void setEquipe2_nom(String equipe2_nom) {
        this.equipe2_nom = equipe2_nom;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTournoi_id() {
        return tournoi_id;
    }

    public void setTournoi_id(int tournoi_id) {
        this.tournoi_id = tournoi_id;
    }

    public int getEquipe1_id() {
        return equipe1_id;
    }

    public void setEquipe1_id(int equipe1_id) {
        this.equipe1_id = equipe1_id;
    }

    public int getEquipe2_id() {
        return equipe2_id;
    }

    public void setEquipe2_id(int equipe2_id) {
        this.equipe2_id = equipe2_id;
    }

    public Date getDate_match() {
        return date_match;
    }

    public void setDate_match(Date date_match) {
        this.date_match = date_match;
    }

    public String getRef_match() {
        return ref_match;
    }

    public void setRef_match(String ref_match) {
        this.ref_match = ref_match;
    }

    public int getScore_a() {
        return score_a;
    }

    public void setScore_a(int score_a) {
        this.score_a = score_a;
    }

    public int getScore_b() {
        return score_b;
    }

    public void setScore_b(int score_b) {
        this.score_b = score_b;
    }
    
    
    



}
