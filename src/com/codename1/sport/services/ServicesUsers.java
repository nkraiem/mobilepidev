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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.sport.entities.User;

import com.codename1.sport.utils.Statics;
import com.codename1.ui.events.ActionListener;


import java.io.IOException;
//import java.sql.Timestamp;
//import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ServicesUsers {

    public User user = null;
    public List<User> us;
    public String result = "";
    public static ServicesUsers instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServicesUsers() {
        req = new ConnectionRequest();
    }

    public static ServicesUsers getInstance() {
        if (instance == null) {
            instance = new ServicesUsers();
        }
        return instance;
    }
    boolean retour = false;

    

    public User ConnexionMobile(String username, String password) {
        String url = Statics.BASE_URL + "/login_mobile/" + username + "/"+password;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                user = parseLogin(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return user;
    }

   


    public boolean Inscrire(User u) {
       

        String url = Statics.BASE_URL + "/inscrireMobile?email=" + u.getEmail()+ "&name=" + u.getName()+ "&password=" + u.getPassword()+"&lastName=" + u.getLastName()+"&pathimage=" + u.getImage(); //création de l'URL
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
    
    public boolean Update(User u) {
       

        String url = Statics.BASE_URL + "/updateUser?email=" + u.getEmail()+ "&name=" + u.getName()+ "&password=" + u.getPassword()+"&lastName=" + u.getLastName()+"&pathimage=" + u.getImage(); //création de l'URL
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
    
    
    

    public boolean SendPdf(String mail) {
       

        String url = Statics.BASE_URL + "/sendpdf/" + mail;
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
    
    public boolean Remove(String mail) {
       

        String url = Statics.BASE_URL + "/removeUser/" + mail; //création de l'URL
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
    
    
    
    public List<User> AllUsers() {
        String url = Statics.BASE_URL + "/getAllUsers";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                us = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return us;
    }
   
    
    public User GetUserByEmail(String email) {
        String url = Statics.BASE_URL + "/getUserByEmail/" +email;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                user = parseLogin(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return user;
    }
    
    
     public User parseLogin(String json) {
        

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> usermap = j.parseJSON(new CharArrayReader(json.toCharArray()));
           

            if (usermap==null) {
                return null;
            }
            float id = Float.parseFloat(usermap.get("id").toString());
              
                    User user=new User();
                    user.setId((int) id);
                   
                    user.setName(usermap.get("name").toString());
                     user.setLastName(usermap.get("lastname").toString());
                    if(usermap.get("role").toString().contains("MANAGER"))
                        user.setRoles("manager");
                    else
                        user.setRoles("user");
                    user.setEmail(usermap.get("email").toString());
                    user.setImage(Statics.uploads+"images/products/"+ usermap.get("image").toString());

                return user;   
                
            
        } catch (Exception ex) {
           return null; 
        }
       

    }
     
     public List<User> parseUsers(String json) {
        ArrayList<User> listUser = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            if (list.size() <= 0) {
                return listUser;
            }
            for (Map<String, Object> obj : list) {
               
                    User user = new User();

                    float id = Float.parseFloat(obj.get("id").toString());
                    System.out.println("id " + id);
                    user.setId((int) id);
                    
                    
                     user.setName(obj.get("name").toString());
                    if(obj.get("role").toString().contains("MANAGER"))
                        user.setRoles("manager");
                    else
                        user.setRoles("user");
                    user.setEmail(obj.get("email").toString());
                    
                    
                    listUser.add(user);
               
            }
        } catch (Exception ex) {
            
        }
        return listUser;

    }
}
