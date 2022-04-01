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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.sport.entities.Commantaires;
import com.codename1.sport.entities.Equipe;
import com.codename1.sport.entities.Publication;
import com.codename1.sport.utils.Statics;
import com.codename1.ui.events.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ServiceEquipe {
    public Equipe equipe = null;
    public List<Equipe> us;
    public String result = "";
    public static ServiceEquipe instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
     boolean retour = false;
    
    private ServiceEquipe() {
        req = new ConnectionRequest();
    }

    //singleton
    public static ServiceEquipe getInstance() {
        if (instance == null) {
            instance = new ServiceEquipe();
        }
        return instance;
    }
    
    
    
     public List<Equipe> AllEquipes() {
        String url = Statics.BASE_URL + "/allEquipes";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                us = parseEquipes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return us;
    }
    
    public boolean add(Equipe e) {
       

        String url = Statics.BASE_URL + "/addEquipe?nom=" + e.getNom(); //création de l'URL
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
    
    
    public boolean edit(Equipe e) {
       

        String url = Statics.BASE_URL + "/editequipe?nom=" + e.getNom()+"&id=" + e.getId(); //crÃ©ation de l'URL
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
    
     public boolean active_suspend(int id, int suspension) {
       

        String url = Statics.BASE_URL + "/active_suspend?suspension=" + suspension+"&id=" + id+"&mail=" + Statics.userconnecter.getEmail(); //crÃ©ation de l'URL
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
       

        String url = Statics.BASE_URL + "/removeEquipe/" + id; //crÃ©ation de l'URL
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
    
    public List<Equipe> parseEquipes(String json) {
        ArrayList<Equipe> listEquipe = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            if (list.size() <= 0) {
                return listEquipe;
            }
            for (Map<String, Object> obj : list) {
               
                    Equipe equipe = new Equipe();

                    float id = Float.parseFloat(obj.get("id").toString());
                    System.out.println("id " + id);
                    equipe.setId((int) id);
                    
                    equipe.setNom(obj.get("nom").toString());
                     
                    float nbr_vic = Float.parseFloat(obj.get("nbr_vic").toString());
                    
                    equipe.setNbr_vic((int) nbr_vic);
                    
                    float nbr_per = Float.parseFloat(obj.get("nbr_per").toString());
                    
                    equipe.setNbr_per((int) nbr_per);
                    
                    float nbr_null = Float.parseFloat(obj.get("nbr_null").toString());
                    
                    equipe.setNbr_null((int) nbr_null);
                    
                    
                    if((obj.get("suspension").toString().equals("false"))){
                         equipe.setSuspension(false);
                    }else{
                         equipe.setSuspension(true);
                    }
                   
                    
                    
                    
                    
                    
                    listEquipe.add(equipe);
               
            }
        } catch (Exception ex) {
            System.out.println("com.codename1.sport.services.ServicesPublications.parsePublications()"); 
        }
        
        return listEquipe;

    }
}
