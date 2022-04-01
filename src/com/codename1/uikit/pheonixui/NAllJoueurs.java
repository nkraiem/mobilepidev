/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.codename1.sport.entities.Equipe;
import com.codename1.sport.entities.Joueur;
import com.codename1.sport.services.ServiceJoueur;
import com.codename1.sport.utils.Statics;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.List;

public class NAllJoueurs extends BaseForm {

    ServiceJoueur serviceJoueur = ServiceJoueur.getInstance();
    Container containerglobal = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    public static Equipe equipe = new Equipe();
    List<Joueur> joueurs = serviceJoueur.AllJoueursByEquipe(equipe.getId());

    public NAllJoueurs() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public NAllJoueurs(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);

        installSidemenu(resourceObjectInstance);
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PUBLIC, e -> {
        });
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new NEquipes().show();
        });

         //barre de recherche 
           getToolbar().addSearchCommand(e -> {
    String text = (String)e.getSource();
   
    chercher(text,resourceObjectInstance);
}, 4);
    }

    private void initGuiBuilderComponents(Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setInlineStylesTheme(resourceObjectInstance);
        setInlineStylesTheme(resourceObjectInstance);
        setTitle("Joueurs");
        setName("TrendingForm");

        addComponent(containerglobal);

        remplir(joueurs, resourceObjectInstance);
        


    }
    
    @Override
    protected boolean isCurrentEquipe() {
        return true;
    }

    void remplir(List<Joueur> lista, com.codename1.ui.util.Resources resourceObjectInstance){
                for (Joueur j : lista) {
            Container containerjoueur = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            containerjoueur.add(new Label("Nom : " + j.getNom()));
            containerjoueur.add(new Label("Prenom : " + j.getPrenom()));
            containerjoueur.add(new Label(j.getEmail()));
            containerjoueur.add(new Label("Numero : " + j.getNumero()));

            Label gui_separator1 = new Label();

            //container modifier supprimer
            Container containersuppmodifier=new Container(new BoxLayout(BoxLayout.X_AXIS));
            
            if(Statics.userconnecter.getRoles().equals("manager"))
            containerjoueur.add(containersuppmodifier);
            //Button Modifier
            Button btnModif = new Button("Modifier");
            
            
            btnModif.setPreferredW(getPreferredW() / 2 - 5);
            containersuppmodifier.add(btnModif);
            btnModif.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    NEditJoueur.joueur=j;
                    new NEditJoueur().show();
                }
            });

            //Button Supprimer
            Button btnSupprimer = new Button("Supprimer");
            containersuppmodifier.add(btnSupprimer);
            btnSupprimer.setPreferredW(getPreferredW() / 2 - 5);
            btnSupprimer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    serviceJoueur.removeJoueur(j.getId());
                    new NAllJoueurs().show();
                }
            });

            //separateur
            gui_separator1.setShowEvenIfBlank(true);
            gui_separator1.setUIID("Separator");
            gui_separator1.setInlineStylesTheme(resourceObjectInstance);
            gui_separator1.setName("separator1");

            containerglobal.add(containerjoueur);
            containerglobal.addComponent(gui_separator1);
        }

    }
    
    
    List<Joueur> listFiltrer;
    private void chercher(String mot, Resources resourceObjectInstance) {
         listFiltrer=new ArrayList<>();
         if (mot.equals("")){
           listFiltrer = joueurs; 
         }else{
             for(Joueur jr:joueurs){
                 if(jr.getNom().contains(mot) || jr.getPrenom().contains(mot) || jr.getEmail().contains(mot) || Integer.toString( jr.getNumero()).contains(mot)){
                   listFiltrer.add(jr);
                 }
             }
            
           //  
             
             
         }
          containerglobal.removeAll();
          remplir(listFiltrer, resourceObjectInstance);
          containerglobal.revalidate();
          
    }
}
