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
import com.codename1.sport.entities.Like;
import com.codename1.sport.entities.Publication;
import com.codename1.sport.entities.User;
import com.codename1.sport.utils.Statics;
import com.codename1.ui.events.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ServiceLike {

    public List<Like> us;
    public int nombre;
    public String result = "";
    public static ServiceLike instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceLike() {
        req = new ConnectionRequest();
    }

    public static ServiceLike getInstance() {
        if (instance == null) {
            instance = new ServiceLike();
        }
        return instance;
    }
    boolean retour = false;
    
      public boolean addlike(Like p) {
       

        String url = Statics.BASE_URL + "/addlike?pub=" + p.getPublicationid()+ "&rate=" + p.getRate(); //création de l'URL
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
      
      public int countLikebypublication(int idpub) {
        String url = Statics.BASE_URL + "/countLikebypublication/" + idpub;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                nombre = parseNombre(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return nombre;
    }
      
      public int countDISLIKEbypublication(int idpub) {
        String url = Statics.BASE_URL + "/countDISLIKEbypublication/" + idpub;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                nombre = parseNombre(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return nombre;
    }
      
       public int parseNombre(String json) {
        

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> likemap = j.parseJSON(new CharArrayReader(json.toCharArray()));
           

            if (likemap==null) {
                return 0;
            }
            return (int) Float.parseFloat(likemap.get("nbrlike").toString());
              
                   
                
            
        } catch (Exception ex) {
           return 0; 
        }
       

    }
       
       public List<Like> getLikes() {
        String url = Statics.BASE_URL + "/allLike";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                us = parselikes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return us;
    }
     
       public List<Like> parselikes(String json) {
        ArrayList<Like> listLike = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            if (list.size() <= 0) {
                return listLike;
            }
            for (Map<String, Object> obj : list) {
               
                    Like lik = new Like();

                    float _rate = Float.parseFloat(obj.get("rate").toString());
                    
                   
                    
                    
                     lik.setRate((int)_rate);
                    
                    
                    
                    listLike.add(lik);
               
            }
        } catch (Exception ex) {
            System.out.println("com.codename1.sport.services.ServicesPublications.parsePublications()"); 
        }
        return listLike;

    }
     
}
