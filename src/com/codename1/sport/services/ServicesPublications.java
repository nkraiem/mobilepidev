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
import com.codename1.sport.entities.Publication;
import com.codename1.sport.entities.Publication;
import com.codename1.sport.entities.Publication;
import com.codename1.sport.entities.User;
import com.codename1.sport.utils.Statics;
import com.codename1.ui.events.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ServicesPublications {
    public Publication publication = null;
    public List<Publication> us;
    public String result = "";
    public static ServicesPublications instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServicesPublications() {
        req = new ConnectionRequest();
    }

    public static ServicesPublications getInstance() {
        if (instance == null) {
            instance = new ServicesPublications();
        }
        return instance;
    }
    boolean retour = false;
    
    public List<Publication> AllPublications() {
        String url = Statics.BASE_URL + "/allpublication";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                us = parsePublications(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return us;
    }
    
     public boolean add(Publication p) {
       

        String url = Statics.BASE_URL + "/addpublication?titre=" + p.getTitre_pub()+ "&contenu=" + p.getContenu_pub()+ "&image=" + p.getImagePub()+"&autheur=" + p.getAutheurPub(); //création de l'URL
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
     
     
     public boolean edit(Publication p) {
       

        String url = Statics.BASE_URL + "/editpublication?titre=" + p.getTitre_pub()+ "&contenu=" + p.getContenu_pub()+ "&image=" + p.getImagePub()+"&autheur=" + p.getAutheurPub()+"&id=" + p.getId(); //création de l'URL
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
    
    
    
    public List<Publication> parsePublications(String json) {
        ArrayList<Publication> listPublication = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            if (list.size() <= 0) {
                return listPublication;
            }
            for (Map<String, Object> obj : list) {
               
                    Publication publication = new Publication();

                    float id = Float.parseFloat(obj.get("id").toString());
                    System.out.println("id " + id);
                    publication.setId((int) id);
                    
                    
                     publication.setAutheurPub(obj.get("autheur").toString());
                    
                    publication.setContenu_pub(obj.get("contenu").toString());
                    
                    Date date;
                    DateFormat formatter;
                formatter = new SimpleDateFormat("yyyy-MM-dd");
                String a=((LinkedHashMap)obj.get("date")).get("date").toString();
                    date = (Date) formatter.parse(((LinkedHashMap)obj.get("date")).get("date").toString());
                    
                    publication.setDatePub(date);
                   
                    
                    publication.setImagePub(Statics.uploads+ obj.get("image").toString());
                    
                    publication.setTitre_pub(obj.get("titre").toString());
                    
                    
                    listPublication.add(publication);
               
            }
        } catch (Exception ex) {
            System.out.println("com.codename1.sport.services.ServicesPublications.parsePublications()"); 
        }
        
        return listPublication;

    }
}
