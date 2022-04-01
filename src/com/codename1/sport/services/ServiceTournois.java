package com.codename1.sport.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.L10NManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.sport.entities.Equipe;
import com.codename1.sport.entities.Publication;
import com.codename1.sport.entities.Tournois;
import com.codename1.sport.utils.Statics;
import com.codename1.ui.events.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class ServiceTournois {
      public Tournois tournois = null;
    public List<Tournois> us;
    public String result = "";
    public static ServiceTournois instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
     boolean retour = false;
    
    private ServiceTournois() {
        req = new ConnectionRequest();
    }

    public static ServiceTournois getInstance() {
        if (instance == null) {
            instance = new ServiceTournois();
        }
        return instance;
    }
    
    public List<Tournois> AllTournois() {
        String url = Statics.BASE_URL + "/allTournois";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                us = parseTournois(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return us;
    }
    
    
    public boolean add(Tournois t) {
        String dt1 = L10NManager.getInstance().formatDateShortStyle(t.getDate_debut());
        String dt2 = L10NManager.getInstance().formatDateShortStyle(t.getDate_fin());
        

        String url = Statics.BASE_URL + "/addTournois?nom=" + t.getNom()+ "&datedebut=" + dt1+ "&datefin=" +dt2+ "&prime=" +t.getPrime(); //création de l'URL
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
    
     public boolean edit(Tournois t) {
       
 String dt1 = L10NManager.getInstance().formatDateShortStyle(t.getDate_debut());
        String dt2 = L10NManager.getInstance().formatDateShortStyle(t.getDate_fin());
        String url = Statics.BASE_URL + "/edittournois?nom=" + t.getNom()+ "&datedebut=" + dt1+ "&datefin=" + dt2+"&prime=" + t.getPrime()+"&id=" + t.getId(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
     public boolean Remove(int id) {
       

        String url = Statics.BASE_URL + "/removeTournois/" + id; //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
     public List<Tournois> parseTournois(String json) {
        ArrayList<Tournois> listTournois = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            if (list.size() <= 0) {
                return listTournois;
            }
            for (Map<String, Object> obj : list) {
               
                    Tournois tournois = new Tournois();

                    float id = Float.parseFloat(obj.get("id").toString());
                    System.out.println("id " + id);
                    tournois.setId((int) id);
                    
                    tournois.setNom(obj.get("nom").toString());
                    
                    Date date;
                    DateFormat formatter;
                formatter = new SimpleDateFormat("yyyy-MM-dd");
                String a=((LinkedHashMap)obj.get("date")).get("date").toString();
                    date = (Date) formatter.parse(((LinkedHashMap)obj.get("date")).get("date").toString());
                    
                      Date date2;
                    DateFormat formatter2;
                formatter2 = new SimpleDateFormat("yyyy-MM-dd");
                String aa=((LinkedHashMap)obj.get("date2")).get("date").toString();
                    date2 = (Date) formatter.parse(((LinkedHashMap)obj.get("date2")).get("date").toString());
                    
                    tournois.setDate_debut(date);
                    tournois.setDate_fin(date2);
                     
                    float prime = Float.parseFloat(obj.get("prime").toString());
                    
                    tournois.setPrime((int) prime);
                    
                    
                    
                    
                    
                    
                    
                    listTournois.add(tournois);
               
            }
        } catch (Exception ex) {
            System.out.println("com.codename1.sport.services.ServicesPublications.parsePublications()"); 
        }
        
        return listTournois;

    }
}
