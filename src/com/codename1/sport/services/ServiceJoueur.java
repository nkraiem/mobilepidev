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

import com.codename1.sport.entities.Joueur;
import com.codename1.sport.utils.Statics;
import com.codename1.ui.events.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ServiceJoueur {

    public Joueur joueur = null;
    public List<Joueur> us;
    public String result = "";
    public static ServiceJoueur instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    boolean retour = false;

    private ServiceJoueur() {
        req = new ConnectionRequest();
    }

    public static ServiceJoueur getInstance() {
        if (instance == null) {
            instance = new ServiceJoueur();
        }
        return instance;
    }

    public boolean add(Joueur p) {

        String url = Statics.BASE_URL + "/addJoueur?nom=" + p.getNom() + "&prenom=" + p.getPrenom() + "&mail=" + p.getEmail() + "&numero=" + p.getNumero() + "&equipeid=" + p.getEquipes_id(); //création de l'URL
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
    
    
        public boolean edit(Joueur p) {

        String url = Statics.BASE_URL + "/editJoueur?nom=" + p.getNom() + "&prenom=" + p.getPrenom() + "&mail=" + p.getEmail() + "&numero=" + p.getNumero() + "&equipeid=" + p.getEquipes_id()+"&id=" + p.getId(); //création de l'URL
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
    
    
    public boolean removeJoueur(int id) {

        String url = Statics.BASE_URL + "/removeJoueur/"+id;
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
    
    public List<Joueur> AllJoueursByEquipe(int idequipe) {
        String url = Statics.BASE_URL + "/alljoueurbyequipe/"+idequipe;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                us = parseJoueurs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return us;
    }

    public List<Joueur> parseJoueurs(String json) {
        ArrayList<Joueur> _listJoueurs = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            if (list.size() <= 0) {
                return _listJoueurs;
            }
            for (Map<String, Object> obj : list) {

                Joueur _j = new Joueur();

                float id = Float.parseFloat(obj.get("id").toString());

                _j.setId((int) id);

                _j.setEmail(obj.get("mail").toString());
                
                _j.setNom(obj.get("nom").toString());
                
                _j.setPrenom(obj.get("prenom").toString());
                
                 

                float numero = Float.parseFloat(obj.get("numero").toString());

                _j.setNumero((int) numero);

                

                _listJoueurs.add(_j);

            }
        } catch (Exception ex) {
            
        }
        return _listJoueurs;

    }

}
