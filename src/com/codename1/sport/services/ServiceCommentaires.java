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
import com.codename1.sport.entities.Publication;
import com.codename1.sport.utils.Statics;
import com.codename1.ui.events.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import java.util.List;
import java.util.Map;


public class ServiceCommentaires {
    
     public Commantaires commentaire = null;
    public List<Commantaires> us;
    public String result = "";
    public static ServiceCommentaires instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
     boolean retour = false;
    
    private ServiceCommentaires() {
        req = new ConnectionRequest();
    }

    public static ServiceCommentaires getInstance() {
        if (instance == null) {
            instance = new ServiceCommentaires();
        }
        return instance;
    }
    
    
         public boolean add(Commantaires p) {
       

        String url = Statics.BASE_URL + "/addCommentaire?pub=" + p.getPublication_id()+ "&contenu=" + p.getContenu(); //création de l'URL
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
         
         public List<Commantaires> AllCommentsByPub(int idpub) {
        String url = Statics.BASE_URL + "/allcommentsbypub/"+idpub;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                us = parseComments(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return us;
    }
         
         
          public List<Commantaires> parseComments(String json) {
        ArrayList<Commantaires> _listCommentaires = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            if (list.size() <= 0) {
                return _listCommentaires;
            }
            for (Map<String, Object> obj : list) {
               
                    Commantaires comm = new Commantaires();

                    float id = Float.parseFloat(obj.get("id").toString());
                    
                    comm.setId((int) id);
                    
                    
                     comm.setContenu(obj.get("contenu").toString());
                    
                     float idpub = Float.parseFloat(obj.get("idpub").toString());
                    
                    
                     
                    comm.setPublication_id((int) idpub);
                    
                    Date date;
                    DateFormat formatter;
                formatter = new SimpleDateFormat("yyyy-MM-dd");
                String a=((LinkedHashMap)obj.get("date")).get("date").toString();
                    date = (Date) formatter.parse(((LinkedHashMap)obj.get("date")).get("date").toString());
                    
                    comm.setDate_commentaire(date);
                   
                    
                   
                    
                    
                    _listCommentaires.add(comm);
               
            }
        } catch (Exception ex) {
            System.out.println("com.codename1.sport.services.ServicesPublications.parsePublications()"); 
        }
        return _listCommentaires;

    }
    
}
