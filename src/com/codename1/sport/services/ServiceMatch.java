/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.sport.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.L10NManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.sport.entities.Match;
import com.codename1.sport.entities.Match;
import com.codename1.sport.utils.Statics;
import com.codename1.ui.events.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ServiceMatch {
   public Match tournois = null;
    public List<Match> us;
    public String result = "";
    public static ServiceMatch instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
     boolean retour = false;
    
    private ServiceMatch() {
        req = new ConnectionRequest();
    }

    public static ServiceMatch getInstance() {
        if (instance == null) {
            instance = new ServiceMatch();
        }
        return instance;
    }
    public boolean add(Match t) {
        
         String dt1 = L10NManager.getInstance().formatDateTimeShort(t.getDate_match());

        String url = Statics.BASE_URL + "/addMatch?ref=" + t.getRef_match()+ "&tournoiid=" + t.getTournoi_id()+ "&equip1=" +t.getEquipe1_id()+ "&equip2=" +t.getEquipe2_id()+ "&date=" +dt1; //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    
     public List<Match> AllMatchs() {
        String url = Statics.BASE_URL + "/allMatchs";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                us = parseMatch(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return us;
    }
    
    public List<Match> AllMatchsbytournois(int id) {
        String url = Statics.BASE_URL + "/allMatchsbytournois/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                us = parseMatch(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return us;
    }
     
     public List<Match> parseMatch(String json) {
         ArrayList<Match> listMatchs = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            if (list.size() <= 0) {
                return listMatchs;
            }
            for (Map<String, Object> obj : list) {
               
                    Match match = new Match();

                    float id = Float.parseFloat(obj.get("id").toString());
                    System.out.println("id " + id);
                    match.setId((int) id);
                    
                    float tournoi_id = Float.parseFloat(obj.get("tournoi_id").toString());
                    System.out.println("tournoi_id " + tournoi_id);
                    match.setTournoi_id((int) tournoi_id);
                    
                    float equipe1_id = Float.parseFloat(obj.get("equipe1_id").toString());
                    System.out.println("equipe1_id " + equipe1_id);
                    match.setEquipe1_id((int) equipe1_id);
                    
                    float equipe2_id = Float.parseFloat(obj.get("equipe2_id").toString());
                    System.out.println("equipe2_id " + equipe2_id);
                    match.setEquipe2_id((int) equipe2_id);
                    
                    
                    match.setRef_match(obj.get("ref_match").toString());
                    
                    Date date;
                    DateFormat formatter;
                formatter = new SimpleDateFormat("yyyy-MM-dd");
                String a=((LinkedHashMap)obj.get("date")).get("date").toString();
                    date = (Date) formatter.parse(((LinkedHashMap)obj.get("date")).get("date").toString());
                    
                match.setDate_match(date);
                    
                     float score_a = Float.parseFloat(obj.get("score_a").toString());
                    System.out.println("score_a " + score_a);
                    match.setScore_a((int) score_a);
                    
                    
                    float score_b = Float.parseFloat(obj.get("score_b").toString());
                    System.out.println("score_a " + score_b);
                    match.setScore_b((int) score_b);
                    
                    match.setEquipe1_nom(obj.get("equipe1_nom").toString());
                    match.setEquipe2_nom(obj.get("equipe2_nom").toString());
                    
                    
                    listMatchs.add(match);
               
            }
        } catch (Exception ex) {
            System.out.println("com.codename1.sport.services.ServicesPublications.parsePublications()"); 
        }
        
        return listMatchs;

    }
 
}
